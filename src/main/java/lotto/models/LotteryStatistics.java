package lotto.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lotto.models.enums.Rank;

public class LotteryStatistics {
    private final Map<Rank, Integer> rankCounts;

    private final Double earningsRate;

    public LotteryStatistics(Goal goal, List<Lottery> lotteries, Integer budget) {
        rankCounts = collectRankCounts(goal, lotteries);
        earningsRate = calculateEarningsRate(budget);
    }

    public Double getEarningsRate() {
        return earningsRate;
    }

    public Integer getCountOf(Rank rank) {
        return Objects.nonNull(rankCounts.get(rank)) ? rankCounts.get(rank) : 0;
    }

    private Map<Rank, Integer> collectRankCounts(Goal goal, List<Lottery> lotteries) {
        Map<Rank, Integer> rankCounts = initializeRankCounts();
        lotteries.forEach((lottery) -> {
            Rank currentKey = goal.compareLottery(lottery);
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

