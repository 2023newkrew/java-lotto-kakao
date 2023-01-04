package buyer;

import lotto.Lotteries;
import lotto.Lotto;

public class Buyer {
    private final Lotteries lotteries = new Lotteries();
    private final Money budget;

    public Buyer(int budget) {
        this.budget = new Money(budget);
    }

    public boolean hasEqualOrMoreBudgetThan(int price) {
        return budget.compareTo(new Money(price)) >= 0;
    }

    public void buyLottery(int lotteryPrice, Lotto lotto) {
        this.budget.decreaseMoney(new Money(lotteryPrice));
        this.lotteries.addLottery(lotto);
    }

    public Lotteries getLotteries() {
        return lotteries;
    }
}
