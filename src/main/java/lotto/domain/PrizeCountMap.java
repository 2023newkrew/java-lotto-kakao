package lotto.domain;

import java.util.HashMap;

public class PrizeCountMap {
    private final HashMap<LottoPrize, Long> prizeCountMap;

    public PrizeCountMap(HashMap<LottoPrize, Long> prizeCountMap) {
        this.prizeCountMap = prizeCountMap;
    }

    public long getTotalPrizeMoney() {
        return prizeCountMap.entrySet().stream()
                .mapToLong(e -> e.getKey().getPrizeMoney() * e.getValue())
                .sum();
    }
}
