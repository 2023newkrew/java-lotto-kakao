package buyer;

import lotto.*;

import java.util.Arrays;
import java.util.EnumMap;

public class BuyerResult {
    private final EnumMap<Rank, Integer> result;

    public BuyerResult(Lotteries lotteries, LotteryResult lotteryResult) {
        result = new EnumMap<>(Rank.class);
        for (Lottery lottery : lotteries.getLotteries()) {
            result.put(lotteryResult.getRank(lottery), result.getOrDefault(lotteryResult.getRank(lottery), 0) + 1);
        }
    }

    public BuyerResult(EnumMap<Rank, Integer> result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BuyerResult)) return false;

        BuyerResult cp = (BuyerResult) obj;

        return this.result.equals(cp.result);
    }

    public Integer getRankCount(Rank rank) {
        return result.getOrDefault(rank, 0);
    }

    public BuyerProfit getProfit() {
        int profit = Arrays.stream(Rank.values())
                .mapToInt(e -> result.getOrDefault(e, 0) * e.prize)
                .sum();
        int count = Arrays.stream(Rank.values()).mapToInt(e -> result.getOrDefault(e, 0)).sum();

        return new BuyerProfit((double) profit / (count * LotteryGenerator.LOTTERY_PRICE));
    }
}
