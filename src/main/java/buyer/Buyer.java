package buyer;

import lotto.Lottery;

import java.util.ArrayList;
import java.util.List;

public class Buyer {
    private final List<Lottery> lotteries = new ArrayList<>();
    private int budget;

    public Buyer(int budget) {
        this.budget = budget;
    }

    public int getBudget() {
        return budget;
    }

    public void decreaseBudgetByPrice(int price) {
        this.budget -= price;
    }

    public List<Lottery> getLotteries() {
        return lotteries;
    }
}
