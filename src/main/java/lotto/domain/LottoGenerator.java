package lotto.domain;

import static lotto.LottoConfig.LOTTO_PRICE;
import static lotto.LottoConfig.LOTTO_SIZE;
import static lotto.constant.MessageConstant.INVALID_PRICE;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {

    private static final List<Integer> fullNumbers = IntStream.range(1, 46)
            .boxed()
            .collect(Collectors.toList());

    public List<Lotto> generateLottos(int price) {
        if (price < LOTTO_PRICE) {
            throw new IllegalArgumentException(INVALID_PRICE);
        }
        return IntStream.range(0, price / LOTTO_PRICE)
                .mapToObj(i -> this.generateLotto())
                .collect(Collectors.toList());
    }

    private Lotto generateLotto() {
        Collections.shuffle(fullNumbers, ThreadLocalRandom.current());
        return new Lotto(fullNumbers.subList(0, LOTTO_SIZE));
    }

}
