package lotto.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.models.enums.Rank;

public class LotteryStatistics {
    private final Map<Rank, Integer> rankCounts;

    public LotteryStatistics(WinningLottery winningLottery, List<Lottery> lotteries) {
        rankCounts = collectRankCounts(winningLottery, lotteries);
    }

    public Integer getCountOf(Rank rank) {
        return rankCounts.getOrDefault(rank, 0);
    }

    private Map<Rank, Integer> collectRankCounts(WinningLottery winningLottery, List<Lottery> lotteries) {
        Map<Rank, Integer> rankCounts = initializeRankCounts();
        lotteries.forEach((lottery) -> {
            Rank currentKey = winningLottery.compareLottery(lottery);
            int currentCount = rankCounts.getOrDefault(currentKey, 0);
            rankCounts.put(currentKey, currentCount + 1);
        });
        return rankCounts;
    }

    private Map<Rank, Integer> initializeRankCounts() {
        Map<Rank, Integer> rankCounts = new HashMap<>();
        for (Rank rank : Rank.values()) {
            rankCounts.put(rank, 0);
        }
        return rankCounts;
    }
}

