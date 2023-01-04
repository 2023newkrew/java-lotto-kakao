package buyer;

import lotto.Lotteries;
import lotto.Lotto;

public class Buyer {
    private final Lotteries lotteries = new Lotteries();
    private final Budget budget;

    public Buyer(int budget) {
        this.budget = new Budget(budget);
    }

    public boolean hasMoreBudgetThan(int price) {
        return budget.hasMoreThan(price);
    }

    public void buyLottery(int lotteryPrice, Lotto lotto) {
        this.budget.decreaseBudget(lotteryPrice);
        this.lotteries.addLottery(lotto);
    }

    public Lotteries getLotteries() {
        return lotteries;
    }
}
