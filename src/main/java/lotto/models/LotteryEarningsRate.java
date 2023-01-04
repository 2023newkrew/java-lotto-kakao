package lotto.models;

import lotto.models.enums.Rank;

public class LotteryEarningsRate {

    private final Double earningsRate;

    public LotteryEarningsRate(LotteryStatistics lotteryStatistics, Integer budget) {
        this.earningsRate = calculateEarningsRate(lotteryStatistics, budget);
    }

    public String getStringUpToTwoDecimalPlaces() {
        return String.format("%.2f", earningsRate);
    }

    private Double calculateEarningsRate(LotteryStatistics lotteryStatistics, Integer budget) {
        long sumOfPrize = 0;
        for (Rank rank : Rank.values()) {
            sumOfPrize += rank.getPrize() * lotteryStatistics.getCountOf(rank);
        }
        return (double) sumOfPrize / budget;
    }
}
