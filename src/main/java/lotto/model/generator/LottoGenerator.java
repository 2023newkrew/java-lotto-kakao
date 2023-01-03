package lotto.model.generator;

import lotto.model.number.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.common.LottoConfiguration.MAX_VALUE;
import static lotto.common.LottoConfiguration.MIN_VALUE;

public interface LottoGenerator {
    static public List<Integer> lottoPreset = initializeLottoPreset();
    public LottoNumber createLotto();

    static private List<Integer> initializeLottoPreset() {
        return IntStream
                .rangeClosed(MIN_VALUE, MAX_VALUE)
                .boxed()
                .collect(Collectors.toList());
    }
}
