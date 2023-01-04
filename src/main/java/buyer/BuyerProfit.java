package buyer;

import lotto.Lottery;

public class BuyerProfit {
    private final double profit;

    public BuyerProfit(int lotteryCount, int totalPrize) {
        profit = (double) totalPrize / (lotteryCount * Lottery.PRICE);
    }

    public BuyerProfit(double profit) {
        this.profit = profit;
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
