package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {

    static final int LOTTO_PRICE = 1000;
    private static final List<Integer> fullNumbers = IntStream.range(1, 46)
            .boxed()
            .collect(Collectors.toList());

    static public List<Lotto> generate(int price) {
        List<Lotto> lottos = new ArrayList<>();
        int lottoCount = price / LOTTO_PRICE;
        IntStream.range(0, lottoCount).forEach((i) -> lottos.add(generate()));
        return lottos;
    }

    private static Lotto generate() {
        Collections.shuffle(fullNumbers);
        return new Lotto(fullNumbers.subList(0, 6));
    }

}
