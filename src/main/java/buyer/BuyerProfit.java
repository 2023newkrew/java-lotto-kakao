package buyer;

import lotto.Lottery;

public class BuyerProfit {
    private final double profit;

    public BuyerProfit(int lotteryCount, int totalPrize) {
        if (lotteryCount == 0) {
            profit = 0;
            return;
        }

        profit = (double) totalPrize / (lotteryCount * Lottery.PRICE);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BuyerProfit)) return false;

        BuyerProfit cp = (BuyerProfit) obj;

        return this.profit == cp.profit;
    }

    public double getProfit() {
        return profit;
    }
}
