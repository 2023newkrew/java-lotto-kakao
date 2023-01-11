package lotto.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Price {
    FIFTH("3개 일치", 5000, 3, true),
    FOURTH("4개 일치", 50000, 4, true),
    THIRD("5개 일치", 1500000, 5, false),
    SECOND("5개 일치, 보너스 볼 일치", 30000000, 5, true),
    FIRST("6개 일치", 2000000000, 6, true),
    NOTHING("", 0, 0, true);

    private final String matchDescription;
    private final long price;
    private final int matchCount;
    private final boolean hasBonus;

    Price(String matchDescription, long price, int matchCount, boolean hasBonus) {
        this.matchDescription = matchDescription;
        this.price = price;
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
    }

    public static Price judgePrice(int matchCount, boolean hasBonus) {
        List<Price> selectedPrice = Arrays.stream(Price.values())
                .filter(price -> price.matchCount == matchCount)
                .collect(Collectors.toList());

        if (selectedPrice.size() == 2) {
            return selectedPrice.stream()
                    .filter(price -> price.hasBonus == hasBonus)
                    .findFirst().get();
        }

        if (selectedPrice.isEmpty()) {
            return Price.NOTHING;
        }

        return selectedPrice.get(0);
    }

    public String matchDescription() {
        return matchDescription;
    }

    public long price() {
        return price;
    }
}
