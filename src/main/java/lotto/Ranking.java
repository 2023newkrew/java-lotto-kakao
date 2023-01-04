package lotto;

import java.util.Map;

/**
 * 로또의 등수와 각 등수의 상금에 대한 정의가 포함되어 있습니다.
 */
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
