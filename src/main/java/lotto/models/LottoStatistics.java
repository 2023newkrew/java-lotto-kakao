package lotto.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LottoStatistics {
    private final Map<LottoResult, Integer> rankCounts;

    private final Double earningsRate;

    public LottoStatistics(Goal goal, List<Lotto> lottos, Integer budget) {
        rankCounts = collectRankCounts(goal, lottos);
        earningsRate = calculateEarningsRate(budget);

    }

    public Double getEarningsRate() {
        return earningsRate;
    }

    public Integer getCountOf(LottoResult rank) {
        return Objects.nonNull(rankCounts.get(rank)) ? rankCounts.get(rank) : 0;
    }

    private Map<LottoResult, Integer> collectRankCounts(Goal goal, List<Lotto> lottos) {
        Map<LottoResult, Integer> rankCounts = initializeRankCounts();
        lottos.forEach((lotto) -> {
            LottoResult currentKey = goal.compareLotto(lotto);
            rankCounts.put(currentKey, rankCounts.get(currentKey) + 1);
        });
        return rankCounts;
    }

    private Double calculateEarningsRate(Integer budget) {
        long sumOfPrize = 0;
        for (LottoResult key : rankCounts.keySet()) {
            long numberOfPrize = rankCounts.get(key);
            sumOfPrize += key.getPrize() * numberOfPrize;
        }
        return (double) sumOfPrize / budget;
    }

    private Map<LottoResult, Integer> initializeRankCounts() {
        Map<LottoResult, Integer> rankCounts = new HashMap<>();
        for (LottoResult lottoResult : LottoResult.values()) {
            rankCounts.put(lottoResult, 0);
        }

        return rankCounts;
    }
}

