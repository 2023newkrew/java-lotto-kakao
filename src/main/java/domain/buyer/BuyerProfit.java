package domain.buyer;

import domain.lotto.Lottery;

public class BuyerProfit {
    private final double profit;

    public BuyerProfit(int lotteryCount, int totalPrize) {
        validateNumber(lotteryCount, totalPrize);

        profit = calculateProfit(lotteryCount, totalPrize);
    }

    private void validateNumber(int lotteryCount, int totalPrize) {
        if (lotteryCount < 0) throw new IllegalArgumentException("로또 개수는 음수일 수 없습니다");
        if (totalPrize < 0) throw new IllegalArgumentException("상금은 음수일 수 없습니다");
    }

    private double calculateProfit(int lotteryCount, int totalPrize) {
        if (lotteryCount == 0) return 0;

        return (double) totalPrize / (lotteryCount * Lottery.PRICE);
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
