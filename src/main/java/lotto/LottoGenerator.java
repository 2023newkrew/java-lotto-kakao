package lotto;

import buyer.Buyer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {
    public final static int LOTTERY_PRICE = 1000;

    public int autoGenerateRemaining(Buyer buyer) {
        int quantity = 0;
        List<Integer> numberCollection = IntStream.range(LottoNumber.MIN_NUMBER, LottoNumber.MAX_NUMBER + 1)
                .boxed()
                .collect(Collectors.toList());

        while (buyer.hasEqualOrMoreBudgetThan(LOTTERY_PRICE)) {
            Collections.shuffle(numberCollection);
            buyer.buyLottery(LOTTERY_PRICE, new Lotto(numberCollection.subList(0, 6)));
            quantity++;
        }
        return quantity;
    }

    public void manuallyGenerateOne(Buyer buyer, String numbersAsString) {
        buyer.buyLottery(LOTTERY_PRICE, new Lotto(numbersAsString));
    }
}
