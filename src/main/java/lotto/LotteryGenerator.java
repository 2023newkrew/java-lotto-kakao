package lotto;

import buyer.Buyer;

public class LotteryGenerator {
    public final static int LOTTERY_PRICE = 1000;

    public void generate(Buyer buyer) {
        while (buyer.hasMoreBudgetThan(LOTTERY_PRICE)) {
            buyer.decreaseBudgetByPrice(LOTTERY_PRICE);
            buyer.addLottery(new Lottery());
        }
    }
}
