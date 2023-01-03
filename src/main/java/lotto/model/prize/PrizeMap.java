package lotto.model.prize;

import java.math.BigDecimal;
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

    protected PrizeMap(Map<Prize, Long> prizeMap) {
        this.prizeMap = prizeMap;
    }


    public long countBy(Prize prize) {
        return prizeMap.getOrDefault(prize, 0L);
    }

    public BigDecimal getTotalProfit() {
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
