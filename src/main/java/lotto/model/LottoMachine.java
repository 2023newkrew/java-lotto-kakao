package lotto.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoMachine {

    private final List<Lotto> issuedLottos;
    private final Money money;

    private LottoMachine(List<Lotto> issuedLottos, Money money) {
        this.issuedLottos = issuedLottos;
        this.money = money;
    }

    public static LottoMachine create(LottosGenerator lottosGenerator, Money money) {
        List<Lotto> issuedLottos = lottosGenerator.generate(money);
        return new LottoMachine(issuedLottos, money);
    }

    public List<Lotto> getIssueLottos() {
        return Collections.unmodifiableList(issuedLottos);
    }

    public WinningStatistics judge(WinningNumbers winningNumbers) {
        Map<Prize, Long> prizeMap = issuedLottos.stream()
                .map(winningNumbers::judge)
                .collect(Collectors.toMap(p -> p, p -> 1L, Long::sum));

        return new WinningStatistics(money, prizeMap);
    }
}
