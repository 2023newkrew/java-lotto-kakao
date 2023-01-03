package lotto.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class PrizeCountMap {
    private final Map<LottoPrize, Integer> prizeCountMap;

    public PrizeCountMap(Map<LottoPrize, Integer> prizeCountMap) {
        this.prizeCountMap = prizeCountMap;
        Arrays.stream(LottoPrize.values())
                .forEachOrdered(e -> prizeCountMap.put(e, prizeCountMap.getOrDefault(e, 0)));
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

    private int getLottoCount() {
        return prizeCountMap.values().stream().mapToInt(Integer::intValue).sum();
    }

    private double getProfit() {
        double totalSpentMoney = getLottoCount() * Money.LOTTO_PRICE;
        return getTotalPrizeMoney() / totalSpentMoney;
    }

    private long getTotalPrizeMoney() {
        return prizeCountMap.entrySet().stream()
                .mapToLong(e -> e.getKey().getPrizeMoney() * e.getValue())
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();

        Arrays.stream(LottoPrize.values())
                .forEachOrdered((e) ->
                        message.append(String.format("%s (%d원) - %d개\n", e.getDescription(), e.getPrizeMoney(), prizeCountMap.get(e)))
                );

        message.append(String.format("총 수익률은 %.2f입니다.\n", getProfit()));

        return message.toString();
    }
}
