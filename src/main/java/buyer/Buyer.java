package buyer;

import lotto.Lotteries;
import lotto.Lotto;

public class Buyer {
    private final Lotteries lotteries = new Lotteries();
    private final Money budget;

    public Buyer(int budget) {
        this.budget = new Money(budget);
    }

    public boolean hasMoreBudgetThan(int price) {
        return budget.isMoreThan(price);
    }

    public void buyLottery(int lotteryPrice, Lotto lotto) {
        this.budget.decreaseMoney(lotteryPrice);
        this.lotteries.addLottery(lotto);
    }

    public Lotteries getLotteries() {
        return lotteries;
    }
}
