package lotto;

import java.math.BigDecimal;
import java.util.Map;

public class Prizes {

    private final Map<Prize, Long> prizeMap;

    public Prizes(Map<Prize, Long> prizeMap) {
        this.prizeMap = prizeMap;
    }

    public Long countBy(Prize prize) {
        return prizeMap.getOrDefault(prize, 0L);
    }
}
