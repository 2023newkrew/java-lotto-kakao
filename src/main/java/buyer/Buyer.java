package buyer;

import lotto.Lotto;
import lotto.WinningLotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Buyer {
    private final List<Lotto> lottos = new ArrayList<>();
    private Money budget;

    public Buyer(int budget) {
        this.budget = Money.valueOf(budget);
    }

    public boolean hasEqualOrMoreBudgetThan(int price) {
        return budget.compareTo(Money.valueOf(price)) >= 0;
    }

    public void buyLottery(int lotteryPrice, Lotto lotto) {
        this.budget = this.budget.decreaseMoney(Money.valueOf(lotteryPrice));
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
