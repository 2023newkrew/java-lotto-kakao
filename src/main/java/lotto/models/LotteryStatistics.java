package lotto.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lotto.models.enums.Rank;

public class LotteryStatistics {
    private final Map<Rank, Integer> rankCounts;

    public LotteryStatistics(WinningLottery winningLottery, List<Lottery> lotteries) {
        rankCounts = collectRankCounts(winningLottery, lotteries);
    }

    public Integer getCountOf(Rank rank) {
        return Objects.nonNull(rankCounts.get(rank)) ? rankCounts.get(rank) : 0;
    }

    private Map<Rank, Integer> collectRankCounts(WinningLottery winningLottery, List<Lottery> lotteries) {
        Map<Rank, Integer> rankCounts = new HashMap<>();
        lotteries.forEach((lottery) -> {
            Rank currentKey = winningLottery.compareLottery(lottery);
            int currentCount = getCountOf(currentKey);
            rankCounts.put(currentKey, currentCount + 1);
        });
        return rankCounts;
    }
}

