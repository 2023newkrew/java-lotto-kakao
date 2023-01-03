package lotto;

import buyer.Buyer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LotteryGenerator {
    public final static int LOTTERY_PRICE = 1000;

    public void generate(Buyer buyer) {
        List<Integer> numberCollection = IntStream.range(1, 46)
                .boxed()
                .collect(Collectors.toList());

        while (buyer.hasMoreBudgetThan(LOTTERY_PRICE)) {
            Collections.shuffle(numberCollection);
            buyer.buyLottery(LOTTERY_PRICE, new Lottery(numberCollection.subList(0, 6)));
        }
    }
}
