package lotto.model;

import lotto.model.WinningNumbers;
import lotto.model.lotto.LottoBundle;
import lotto.model.vo.Money;
import lotto.model.prize.Prize;
import lotto.model.WinningStatistics;

import java.util.Map;
import java.util.stream.Collectors;

public class LottoCompany {

    private final WinningNumbers winningNumbers;

    public LottoCompany(WinningNumbers winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public WinningStatistics judge(LottoBundle lottoBundle, Money money) {
        Map<Prize, Long> prizeMap = lottoBundle.stream()
                .map(winningNumbers::judge)
                .collect(Collectors.toMap(p -> p, p -> 1L, Long::sum));

        return new WinningStatistics(money, prizeMap);
    }
}
