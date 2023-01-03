package lotto.model;

import lotto.model.prize.Prize;
import lotto.model.prize.PrizeMap;
import lotto.model.vo.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WinningStatistics {

    private final PrizeMap prizeMap;

    private final BigDecimal profitRate;

    public static WinningStatistics from(Money paidMoney, Money totalPrice, PrizeMap prizeMap) {
        BigDecimal profitRate = getProfitRate(paidMoney, totalPrice, prizeMap);

        return new WinningStatistics(prizeMap, profitRate);
    }

    private static BigDecimal getProfitRate(Money paidMoney, Money totalPrice, PrizeMap prizeMap) {
        BigDecimal baseMoney = paidMoney.bigDecimal();
        BigDecimal remainMoney = baseMoney.subtract(totalPrice.bigDecimal());
        BigDecimal totalProfit = prizeMap.getTotalProfit();

        return totalProfit.add(remainMoney).divide(baseMoney, 2, RoundingMode.DOWN);
    }

    private WinningStatistics(PrizeMap prizeMap, BigDecimal profitRate) {
        this.prizeMap = prizeMap;
        this.profitRate = profitRate;
    }

    public long countBy(Prize prize) {
        return prizeMap.countBy(prize);
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }
}
