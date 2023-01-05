package domain.buyer;

import domain.lotto.Lottery;

public class BuyingPlan {
    private final Budget budget;
    private final int manualCount;
    private final int autoCount;

    public BuyingPlan(int budget, int manualCount) {
        this.budget = new Budget(budget);
        if (this.budget.getAbleLotteryCount(Lottery.PRICE) < manualCount)
            throw new IllegalArgumentException("금액으로 구매할 수 없는 개수입니다");

        this.manualCount = manualCount;
        this.autoCount = this.budget.getAbleLotteryCount(Lottery.PRICE) - manualCount;
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }

    public int getTotalCount() {
        return manualCount + autoCount;
    }
}
