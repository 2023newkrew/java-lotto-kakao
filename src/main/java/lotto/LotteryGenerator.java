package lotto;

import buyer.Buyer;

public class LotteryGenerator {
    private final static int LOTTERY_PRICE = 1000;

    public void generate(Buyer buyer) {
        while (buyer.getBudget() >= 1000) {
            buyer.decreaseBudgetByPrice(LOTTERY_PRICE);
            buyer.addLottery(new Lottery());
        }
    }
}
