package lotto;

import buyer.Buyer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {
    public int autoGenerateRemaining(Buyer buyer) {
        int quantity = 0;
        List<Integer> numberCollection = IntStream.range(LottoNumber.MIN_NUMBER, LottoNumber.MAX_NUMBER + 1)
                .boxed()
                .collect(Collectors.toList());

        while (buyer.canBuyLottosOfQuantity(1)) {
            Collections.shuffle(numberCollection);
            buyer.buyLottery(new Lotto(numberCollection.subList(0, 6)));
            quantity++;
        }
        return quantity;
    }

    public void manuallyGenerate(Buyer buyer, List<String> numberStrings) {
        for (String numbersAsString : numberStrings) {
            manuallyGenerateOne(buyer, numbersAsString);
        }
    }

    public void manuallyGenerateOne(Buyer buyer, String numbersAsString) {
        buyer.buyLottery(new Lotto(numbersAsString));
    }
}
