package lotto.model.generator;

import lotto.model.number.LottoNumber;

import static lotto.common.LottoConfiguration.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AutoLottoGenerator implements LottoGenerator {
    public LottoNumber createLotto() {
        Collections.shuffle(lottoPreset);
        List<Integer> numbers = lottoPreset.subList(0, LOTTO_COUNT.getValue()).stream()
                .sorted()
                .collect(Collectors.toList());

        return new LottoNumber(numbers);
    }
}