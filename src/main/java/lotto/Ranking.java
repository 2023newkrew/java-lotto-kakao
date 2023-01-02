package lotto;

import java.util.Map;

public enum Ranking {
    FIRST(2000000000), SECOND(30000000), THIRD(1500000), FOURTH(50000), FIFTH(5000), OTHER(0);

    private final int price;

    Ranking(int price) {
        this.price = price;
    }

    public static Price totalPrice(Map<Ranking, Integer> rankingMap) {
        Price result = new Price(0);
        for (Map.Entry<Ranking, Integer> rankingIntegerEntry : rankingMap.entrySet()) {
            result.add(rankingIntegerEntry.getKey().price * rankingIntegerEntry.getValue());
        }
        return result;
    }
}
