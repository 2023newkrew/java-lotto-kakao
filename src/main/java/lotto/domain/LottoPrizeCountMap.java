package lotto.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class LottoPrizeCountMap {
    private final Map<LottoPrize, Integer> lottoPrizeCountMap;

    public LottoPrizeCountMap(Map<LottoPrize, Integer> lottoPrizeCountMap) {
        this.lottoPrizeCountMap = lottoPrizeCountMap;
        Arrays.stream(LottoPrize.values())
                .forEachOrdered(e -> lottoPrizeCountMap.put(e, lottoPrizeCountMap.getOrDefault(e, 0)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoPrizeCountMap that = (LottoPrizeCountMap) o;
        return Objects.equals(lottoPrizeCountMap, that.lottoPrizeCountMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoPrizeCountMap);
    }

    private int getLottoCount() {
        return lottoPrizeCountMap.values().stream().mapToInt(Integer::intValue).sum();
    }

    private double getProfit() {
        double totalSpentMoney = getLottoCount() * LottoSeller.LOTTO_PRICE;
        return getTotalPrizeMoney() / totalSpentMoney;
    }

    private long getTotalPrizeMoney() {
        return lottoPrizeCountMap.entrySet().stream()
                .mapToLong(e -> e.getKey().getPrizeMoney() * e.getValue())
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();

        Arrays.stream(LottoPrize.values())
                .forEachOrdered((e) ->
                        message.append(String.format("%s (%d원) - %d개\n", e.getDescription(), e.getPrizeMoney(), lottoPrizeCountMap.get(e)))
                );

        message.append(String.format("총 수익률은 %.2f입니다.\n", getProfit()));

        return message.toString();
    }
}
