package lotto.model;

import java.util.*;

public class PriceResult {
    private final Map<Price, Integer> result;

    public PriceResult() {
        result = new EnumMap<>(Price.class);
        for (Price price : Price.values()) {
            if (price != Price.NOTHING) {
                result.put(price, 0);
            }
        }
    }

    public Map<Price, Integer> getResult() {
        return result;
    }

    public void addResult(Price price) {
        if (price != Price.NOTHING) {
            result.put(price, result.get(price) + 1);
        }
    }

    public double calculateEarningsRate(long purchaseCost) {
        return (double) sumPrice() / purchaseCost;
    }

    private long sumPrice() {
        long sumOfPrice = 0;
        for (Price price : result.keySet()) {
            sumOfPrice += result.get(price) * price.price();
        }
        return sumOfPrice;
    }
}
