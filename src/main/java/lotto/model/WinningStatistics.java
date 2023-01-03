package lotto.model;

import lotto.model.prize.Prize;
import lotto.model.prize.PrizeMap;
import lotto.model.vo.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Map;

public class WinningStatistics {

    private static final long LOTTO_PRICE = 1000L;

    private final Money money;

    private final PrizeMap prizeMap;

    public WinningStatistics(Money money, PrizeMap prizeMap) {
        this.money = money;
        this.prizeMap = prizeMap;
    }

    public Long countBy(Prize prize) {
        return prizeMap.countBy(prize);
    }

    public BigDecimal getProfitRate() {
        BigDecimal baseMoney = BigDecimal.valueOf(money.longValue());
        BigDecimal remainMoney = BigDecimal.valueOf(money.longValue() % LOTTO_PRICE);
        BigDecimal totalProfit = getTotalProfit();
        return totalProfit.add(remainMoney)
                .divide(baseMoney, 2, RoundingMode.DOWN);
    }

    private BigDecimal getTotalProfit() {
        return Arrays.stream(Prize.values())
                .map(this::sumEachProfit)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal sumEachProfit(Prize p) {
        BigDecimal amount = BigDecimal.valueOf(p.getAmount());
        BigDecimal quantity = BigDecimal.valueOf(countBy(p));
        return amount.multiply(quantity);
    }
}
