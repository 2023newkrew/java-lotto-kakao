package lotto.core;

import java.util.Map;

/**
 * 로또의 등수와 각 등수의 상금에 대한 정의가 포함되어 있습니다.
 */
public enum Ranking {
    FIRST(20_0000_0000),
    SECOND(3000_0000),
    THIRD(150_0000),
    FOURTH(5_0000),
    FIFTH(5000),
    OTHER(0);

    private final long price;

    Ranking(long price) {
        this.price = price;
    }

    public static long totalPrice(Map<Ranking, Integer> rankingMap) {
        return rankingMap.entrySet()
                .stream()
                .reduce(
                        0L,
                        (acc, other) -> acc + other.getKey().price * other.getValue(),
                        Long::sum
                );
    }

    public long getPrice() {
        return price;
    }
}
