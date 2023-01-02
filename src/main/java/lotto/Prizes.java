package lotto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

public class Prizes {

    private final Map<Prize, Long> prizeMap;

    public Prizes(Map<Prize, Long> prizeMap) {
        this.prizeMap = prizeMap;
    }

    public Long countBy(Prize prize) {
        return prizeMap.getOrDefault(prize, 0L);
    }

    public BigDecimal getTotalAmount() {
        return Arrays.stream(Prize.values())
                .map(this::sumEachPrizeAmount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal sumEachPrizeAmount(Prize p) {
        BigDecimal amount = BigDecimal.valueOf(p.getAmount());
        BigDecimal quantity = BigDecimal.valueOf(countBy(p));
        return amount.multiply(quantity);
    }
}
