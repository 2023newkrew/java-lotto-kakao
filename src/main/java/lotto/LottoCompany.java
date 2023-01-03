package lotto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoCompany {

    private final WinningNumbers winningNumbers;

    private final LottosGenerator lottosGenerator;

    public LottoCompany(WinningNumbers winningNumbers, LottosGenerator lottosGenerator) {
        this.winningNumbers = winningNumbers;
        this.lottosGenerator = lottosGenerator;
    }

    public WinningStatistics play(Money money) {
        long lottoCount = money.getLottoCount();
        List<Lotto> lottos = lottosGenerator.generate(lottoCount);
        return judge(lottos, money);
    }

    private WinningStatistics judge(List<Lotto> lottos, Money money) {
        Map<Prize, Long> prizeMap = lottos.stream()
                .map(winningNumbers::judge)
                .collect(Collectors.toMap(p -> p, p -> 1L, Long::sum));

        return new WinningStatistics(money, prizeMap);
    }
}
