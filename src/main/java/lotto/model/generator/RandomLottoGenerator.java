package lotto.model.generator;

import lotto.model.vo.LottoNumber;
import lotto.model.lotto.Lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class RandomLottoGenerator implements LottoGenerator {

    private static final LottoGenerator instance = new RandomLottoGenerator();

    public static LottoGenerator getInstance() {
        return instance;
    }

    private RandomLottoGenerator() {
    }

    @Override
    public Lotto generate() {
        List<LottoNumber> numberPool = LottoNumber.createNumberPool();
        Collections.shuffle(numberPool);
        return Lotto.from(numberPool.subList(0, Lotto.SIZE));
    }
}