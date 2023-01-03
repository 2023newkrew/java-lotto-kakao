package lotto.model;

import lotto.model.lotto.LottoBundle;
import lotto.model.prize.PrizeMap;
import lotto.model.vo.Money;
import lotto.model.prize.Prize;

import java.util.Map;
import java.util.stream.Collectors;

public class LottoCompany {

    private final WinningNumber winningNumber;

    public LottoCompany(WinningNumber winningNumber) {
        this.winningNumber = winningNumber;
    }

    public WinningStatistics judge(LottoBundle lottoBundle, Money money) {
        PrizeMap prizeMap = winningNumber.judge(lottoBundle);

        return new WinningStatistics(money, prizeMap);
    }
}
