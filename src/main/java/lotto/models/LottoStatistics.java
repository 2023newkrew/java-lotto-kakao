package lotto.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lotto.models.enums.Rank;

public class LottoStatistics {
    private final Map<Rank, Integer> rankCounts;

    private final Double earningsRate;

    public LottoStatistics(Goal goal, List<Lotto> lottos, Integer budget) {
        rankCounts = collectRankCounts(goal, lottos);
        earningsRate = calculateEarningsRate(budget);
    }

    public Double getEarningsRate() {
        return earningsRate;
    }

    public Integer getCountOf(Rank rank) {
        return Objects.nonNull(rankCounts.get(rank)) ? rankCounts.get(rank) : 0;
    }

    private Map<Rank, Integer> collectRankCounts(Goal goal, List<Lotto> lottos) {
        Map<Rank, Integer> rankCounts = initializeRankCounts();
        lottos.forEach((lotto) -> {
            Rank currentKey = goal.compareLotto(lotto);
            rankCounts.put(currentKey, rankCounts.get(currentKey) + 1);
        });
        return rankCounts;
    }

    private Double calculateEarningsRate(Integer budget) {
        long sumOfPrize = 0;
        for (Rank key : rankCounts.keySet()) {
            long numberOfPrize = rankCounts.get(key);
            sumOfPrize += key.getPrize() * numberOfPrize;
        }
        return (double) sumOfPrize / budget;
    }

    private Map<Rank, Integer> initializeRankCounts() {
        Map<Rank, Integer> rankCounts = new HashMap<>();
        for (Rank rank : Rank.values()) {
            rankCounts.put(rank, 0);
        }

        return rankCounts;
    }
}

