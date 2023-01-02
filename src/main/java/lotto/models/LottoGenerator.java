package lotto.models;

import static lotto.common.LottoConfiguration.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {
    private final List<Integer> lottoPreset;

    public LottoGenerator() {
        lottoPreset = IntStream
                .rangeClosed(MIN_VALUE, MAX_VALUE)
                .boxed()
                .collect(Collectors.toList());
    }

    public Lotto createLotto() {
        Collections.shuffle(lottoPreset);

        List<Integer> numbers = lottoPreset.subList(0, LOTTO_COUNT).stream()
                .sorted()
                .collect(Collectors.toList());

        return new Lotto(numbers);
    }
}