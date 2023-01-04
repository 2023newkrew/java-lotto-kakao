package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Issuer {

    private static final List<Integer> numberPool = new ArrayList<>();

    static {
        IntStream.range(LottoNumber.MIN_RANGE, LottoNumber.MAX_RANGE)
                .forEach(numberPool::add);
    }

    private Issuer() {
        throw new UnsupportedOperationException();
    }

    private static Lotto issue() {
        Collections.shuffle(numberPool);
        List<Integer> numbers = new ArrayList<>(numberPool.subList(0, Lotto.NUMBER_LENGTH));
        Collections.sort(numbers);
        return new Lotto(numbers);
    }

    public static LottoList issue(Integer count) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoList.add(issue());
        }
        return new LottoList(lottoList);
    }
}
