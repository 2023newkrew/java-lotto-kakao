package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PrizeCountMap {

    private final Map<LottoPrize, Integer> prizeCountMap;

    public PrizeCountMap(Map<LottoPrize, Integer> prizeCountMap) {
        this.prizeCountMap = new HashMap<>(prizeCountMap);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PrizeCountMap that = (PrizeCountMap) o;
        return Objects.equals(prizeCountMap, that.prizeCountMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prizeCountMap);
    }

    private int getLottoCount() {
        return prizeCountMap.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private double getProfit() {
        double totalSpentMoney = getLottoCount() * Store.LOTTO_PRICE;
        return getTotalPrizeMoney() / totalSpentMoney;
    }

    private long getTotalPrizeMoney() {
        return Arrays.stream(LottoPrize.values())
                .mapToLong(e ->
                        e.getPrizeMoney() * this.prizeCountMap.getOrDefault(e, 0)
                )
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();

        Arrays.stream(LottoPrize.values())
                .forEachOrdered((e) ->
                        message.append(String.format("%s (%d원) - %d개\n",
                                e.getDescription(), e.getPrizeMoney(),
                                prizeCountMap.getOrDefault(e, 0)))
                );
        message.append(String.format("총 수익률은 %.2f입니다.\n", getProfit()));

        return message.toString();
    }
}
