package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lotto.domain.constant.LottoRule;

public class PrizeGroupingMap {

    private final Map<LottoPrize, List<Lotto>> prizeGroupingMap;

    public PrizeGroupingMap(Map<LottoPrize, List<Lotto>> prizeGroupingMap) {
        this.prizeGroupingMap = new HashMap<>(prizeGroupingMap);
    }

    private double getProfit() {
        double totalSpentMoney = getLottoCount() * LottoRule.PRICE;
        return getTotalPrizeMoney() / totalSpentMoney;
    }

    private int getLottoCount() {
        return prizeGroupingMap.values().stream()
                .mapToInt(List::size)
                .sum();
    }

    private long getTotalPrizeMoney() {
        return Arrays.stream(LottoPrize.values())
                .mapToLong(e ->
                        e.getPrizeMoney() * this.prizeGroupingMap.getOrDefault(e, List.of()).size()
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
                                prizeGroupingMap.getOrDefault(e, List.of()).size()))
                );
        message.append(String.format("총 수익률은 %.2f입니다.\n", getProfit()));

        return message.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PrizeGroupingMap that = (PrizeGroupingMap) o;
        return Objects.equals(prizeGroupingMap, that.prizeGroupingMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prizeGroupingMap);
    }
}
