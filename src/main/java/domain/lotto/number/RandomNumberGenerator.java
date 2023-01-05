package domain.lotto.number;

import domain.lotto.LottoMetaData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumberGenerator implements NumberGeneratable {
    private final int MIN_LOTTO_NUMBER = 1;
    private final int MAX_LOTTO_NUMBER = 45;
    private final int LOTTO_NUMBER_SIZE = 6;

    private List<Integer> lottoNumberPool = new ArrayList<>(
            IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER+1).
                    boxed()
                    .collect(Collectors.toList()));

    @Override
    public List<Integer> generate() {
        Collections.shuffle(lottoNumberPool);
        return lottoNumberPool.subList(0, LOTTO_NUMBER_SIZE);
    }
}
