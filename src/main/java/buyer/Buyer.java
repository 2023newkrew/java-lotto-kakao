package buyer;

import lotto.Lotteries;
import lotto.Lotto;

public class Buyer {
    private final Lotteries lotteries = new Lotteries();
    private Money budget;

    public Buyer(int budget) {
        this.budget = Money.valueOf(budget);
    }

    public boolean hasEqualOrMoreBudgetThan(int price) {
        return budget.compareTo(Money.valueOf(price)) >= 0;
    }

    public void buyLottery(int lotteryPrice, Lotto lotto) {
        this.budget = this.budget.decreaseMoney(Money.valueOf(lotteryPrice));
        this.lotteries.addLottery(lotto);
    }

    public Lotteries getLotteries() {
        return lotteries;
    }
}
