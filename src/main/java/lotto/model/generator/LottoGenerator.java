package lotto.model.generator;

import lotto.model.number.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.common.LottoConfiguration.MAX_VALUE;
import static lotto.common.LottoConfiguration.MIN_VALUE;

public interface LottoGenerator {
    List<Integer> lottoPreset = initializeLottoPreset();
    LottoNumber createLotto();

    static private List<Integer> initializeLottoPreset() {
        return IntStream
                .rangeClosed(MIN_VALUE.getValue(), MAX_VALUE.getValue())
                .boxed()
                .collect(Collectors.toList());
    }
}
