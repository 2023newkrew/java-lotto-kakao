package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.constant.LottoRule;

public class PrizeGroupingMap {

    private final Map<LottoPrize, List<Lotto>> prizeGroupingMap;

    public PrizeGroupingMap(Map<LottoPrize, List<Lotto>> prizeGroupingMap) {
        this.prizeGroupingMap = new HashMap<>(prizeGroupingMap);
    }

    public double getProfit() {
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
}
