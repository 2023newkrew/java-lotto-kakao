package lotto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Map;

public class WinningStatistics {

    private final Money money;

    private final Map<Prize, Long> prizeMap;

    public WinningStatistics(Money money, Map<Prize, Long> prizeMap) {
        this.money = money;
        this.prizeMap = prizeMap;
    }

    public Long countBy(Prize prize) {
        return prizeMap.getOrDefault(prize, 0L);
    }

    public BigDecimal getProfitRate() {
        BigDecimal baseMoney = BigDecimal.valueOf(money.getAmount());
        BigDecimal remainMoney = BigDecimal.valueOf(money.getRemain());
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
