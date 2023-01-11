package lotto.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class RandomLottosGenerator implements LottosGenerator {

    @Override
    public List<Lotto> generate(Money money) {
        long count = money.getLottoCount();

        return LongStream.range(0, count)
                .mapToObj(i -> Lotto.createRandomLotto())
                .collect(Collectors.toList());
    }
}
