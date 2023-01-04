package lotto.factory;

import static lotto.domain.LottoConstants.LOTTO_MIN_INDEX;
import static lotto.domain.LottoConstants.LOTTO_NUMBERS_LENGTH;
import static lotto.domain.LottoConstants.LOTTO_NUMBER_LOWER_BOUND;
import static lotto.domain.LottoConstants.LOTTO_NUMBER_UPPER_BOUND;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.LottoNumbers;

public class LottoNumbersFactory {
    public final static List<Integer> list = IntStream.range(LOTTO_NUMBER_LOWER_BOUND, LOTTO_NUMBER_UPPER_BOUND + 1)
            .boxed()
            .collect(Collectors.toList());

    public static LottoNumbers create() {
        Collections.shuffle(list);
        List<Integer> subList = list.subList(LOTTO_MIN_INDEX, LOTTO_MIN_INDEX + LOTTO_NUMBERS_LENGTH);
        return new LottoNumbers(subList);
    }
}
