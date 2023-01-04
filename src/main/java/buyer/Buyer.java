package buyer;

import lotto.Lotteries;
import lotto.Lottery;
import lotto.LotteryDTO;
import lotto.LotteryResult;

import java.util.List;

public class Buyer {
    private final Lotteries lotteries = new Lotteries();
    private final Budget budget;

    public Buyer(int budget) {
        this.budget = new Budget(budget);
    }

    public boolean hasMoreBudgetThan(int price) {
        return budget.hasMoreThan(price);
    }

    public void buyLottery(Lottery lottery) {
        this.budget.decreaseBudget(Lottery.PRICE);

        lotteries.addLottery(lottery);
    }

    public List<LotteryDTO> getLotteries() {
        return lotteries.getLotteryDTOs();
    }

    public BuyerResult getBuyerResult(LotteryResult lotteryResult) {
        return new BuyerResult(lotteries, lotteryResult);
    }
}
