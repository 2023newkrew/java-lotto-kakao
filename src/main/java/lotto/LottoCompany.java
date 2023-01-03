package lotto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoCompany {

    private static final long LOTTO_PRICE = 1000L;

    private final WinningNumbers winningNumbers;

    private final LottoGenerator lottoGenerator;

    public LottoCompany(WinningNumbers winningNumbers, LottoGenerator lottoGenerator) {
        this.winningNumbers = winningNumbers;
        this.lottoGenerator = lottoGenerator;
    }

    public WinningStatistics play(long money) {
        if (money <= 0) {
            throw new IllegalArgumentException("구매 금액은 0원을 초과해야 합니다.");
        }
        long lottoCount = money / LOTTO_PRICE;
        List<Lotto> lottos = lottoGenerator.generate(lottoCount);

        return judge(lottos);
    }

    private WinningStatistics judge(List<Lotto> lottos) {
        Map<Prize, Long> prizeMap = lottos.stream()
                .map(winningNumbers::judge)
                .collect(Collectors.toMap(p -> p, p -> 1L, Long::sum));

        return new WinningStatistics(prizeMap);
    }
}
