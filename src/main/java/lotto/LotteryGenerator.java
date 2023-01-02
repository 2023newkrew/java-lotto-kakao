package lotto;

import buyer.Buyer;

import java.util.ArrayList;
import java.util.List;

public class LotteryGenerator {
    private final static int LOTTERY_PRICE = 1000;

    public List<Lottery> generate(Buyer buyer) {
        List<Lottery> generatedLotteries = new ArrayList<>();
        while (buyer.getBudget() > 0) {
            buyer.decreaseBudgetByPrice(LOTTERY_PRICE);
            generatedLotteries.add(new Lottery());
        }
        return generatedLotteries;
    }
}
