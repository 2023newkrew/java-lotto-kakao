package lotto.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class RandomLottosGenerator implements LottosGenerator {

    @Override
    public List<Lotto> generate(long count) {
        if (count <= 0) {
            throw new IllegalArgumentException("생성하려는 로또는 1개 이상이어야 합니다.");
        }

        return LongStream.range(0, count)
                .mapToObj(ignore -> LottoNumber.generateRandomNumbers(Lotto.SIZE))
                .map(Lotto::from)
                .collect(Collectors.toList());
    }
}
