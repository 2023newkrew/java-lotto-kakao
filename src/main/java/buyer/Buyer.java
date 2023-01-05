package buyer;

import lotto.Lotto;
import lotto.WinningLotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Buyer {
    public final static int LOTTERY_PRICE = 1000;

    private final List<Lotto> lottos = new ArrayList<>();
    private Money budget;

    public Buyer(int budget) {
        this.budget = Money.valueOf(budget);
    }

    public boolean hasEqualOrMoreBudgetThan(int price) {
        return budget.compareTo(Money.valueOf(price)) >= 0;
    }

    public boolean canBuyLottosOfQuantity(int quantity) {
        return hasEqualOrMoreBudgetThan(LOTTERY_PRICE * quantity);
    }

    public void buyLottery(Lotto lotto) {
        this.budget = this.budget.decreaseMoney(Money.valueOf(LOTTERY_PRICE));
        this.lottos.add(lotto);
    }


    public BuyerResult getResult(WinningLotto winningLotto) {
        BuyerResult buyerResult = new BuyerResult();
        for (Lotto lotto : lottos) {
            buyerResult.matches(winningLotto.getRank(lotto));
        }
        return buyerResult;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
