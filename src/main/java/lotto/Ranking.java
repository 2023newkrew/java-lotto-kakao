package lotto;

import java.util.Map;

public enum Ranking {
    FIRST(new Price(2000000000)),
    SECOND(new Price(30000000)),
    THIRD(new Price(1500000)),
    FOURTH(new Price(50000)),
    FIFTH(new Price(5000)),
    OTHER(new Price(0));

    private final Price price;

    Ranking(Price price) {
        this.price = price;
    }

    public static Price totalPrice(Map<Ranking, Integer> rankingMap) {
        return rankingMap.entrySet()
                .stream()
                .reduce(
                        new Price(0),
                        (acc, other) -> acc.add(other.getKey().price.multiply(other.getValue())),
                        Price::add
                );
    }

    public Price intoPrice() {
        return this.price;
    }
}
