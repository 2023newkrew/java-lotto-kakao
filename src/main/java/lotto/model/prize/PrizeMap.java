package lotto.model.prize;

import lotto.model.vo.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class PrizeMap {

    private final Map<Prize, Long> prizeMap;

    public static PrizeMap from(List<Prize> prizes) {
        if (Objects.isNull(prizes)) {
            return new PrizeMap(new HashMap<>());
        }
        Map<Prize, Long> prizeMap = prizes.stream()
                .collect(Collectors.toMap(p -> p, p -> 1L, Long::sum));
        return new PrizeMap(prizeMap);
    }

    private PrizeMap(Map<Prize, Long> prizeMap) {
        this.prizeMap = prizeMap;
    }


    public Long countBy(Prize prize) {
        return prizeMap.getOrDefault(prize, 0L);
    }

    public BigDecimal getProfitRate(Money paidMoney, Money totalPrice) {
        BigDecimal baseMoney = paidMoney.bigDecimal();
        BigDecimal remainMoney = baseMoney.subtract(totalPrice.bigDecimal());
        BigDecimal totalProfit = getTotalProfit();
        return totalProfit.add(remainMoney).divide(baseMoney, 2, RoundingMode.DOWN);
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
