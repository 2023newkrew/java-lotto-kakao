package buyer;

import lotto.Lotteries;
import lotto.Lottery;

public class Buyer {
    private final Lotteries lotteries = new Lotteries();
    private final Budget budget;

    public Buyer(int budget) {
        this.budget = new Budget(budget);
    }

    public boolean hasMoreBudgetThan(int price) {
        return budget.hasMoreThan(price);
    }

    public void buyLottery(int price, Lottery lottery) {
        this.budget.decreaseBudget(price);

        lotteries.addLottery(lottery);
    }

    public Lotteries getLotteries() {
        return lotteries;
    }
}
