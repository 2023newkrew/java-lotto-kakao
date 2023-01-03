package lotto.domain;

import java.util.Map;
import java.util.Objects;

public class PrizeCountMap {
    private final Map<LottoPrize, Integer> prizeCountMap;

    public PrizeCountMap(Map<LottoPrize, Integer> prizeCountMap) {
        this.prizeCountMap = prizeCountMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrizeCountMap that = (PrizeCountMap) o;
        return Objects.equals(prizeCountMap, that.prizeCountMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prizeCountMap);
    }

    public long getTotalPrizeMoney() {
        return prizeCountMap.entrySet().stream()
                .mapToLong(e -> e.getKey().getPrizeMoney() * e.getValue())
                .sum();
    }
}
