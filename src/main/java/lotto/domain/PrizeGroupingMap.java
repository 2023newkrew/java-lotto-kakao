package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lotto.domain.constant.LottoRule;

public class PrizeGroupingMap {

    private final Map<LottoPrize, Long> prizeGroupingMap;

    public PrizeGroupingMap(Map<LottoPrize, Long> prizeGroupingMap) {
        this.prizeGroupingMap = new HashMap<>(prizeGroupingMap);
    }

    public double getProfit() {
        double totalSpentMoney = getLottoCount() * LottoRule.PRICE;
        return getTotalPrizeMoney() / totalSpentMoney;
    }

    public int getOrDefault(LottoPrize prize, Long defaultValue) {
        return prizeGroupingMap.getOrDefault(prize, defaultValue).intValue();
    }

    private int getLottoCount() {
        return prizeGroupingMap.values().stream()
                .mapToInt(Long::intValue)
                .sum();
    }

    private long getTotalPrizeMoney() {
        return Arrays.stream(LottoPrize.values())
                .mapToLong(e ->
                        e.getPrizeMoney() * this.prizeGroupingMap.getOrDefault(e, 0L)
                )
                .sum();
    }
}
