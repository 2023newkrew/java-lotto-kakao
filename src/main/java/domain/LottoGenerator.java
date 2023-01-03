package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {
    private static final Integer LOTTO_SIZE = 6;
    private static final Integer LOTTO_NUMBER_START = 1;
    private static final Integer LOTTO_NUMBER_END = 45;

    private static final List<LottoNumber> LOTTO_NUMBERS = IntStream
            .range(LOTTO_NUMBER_START, LOTTO_NUMBER_END + 1)
            .mapToObj(LottoNumber::new)
            .collect(Collectors.toList());

    public static List<LottoNumber> run() {
        Collections.shuffle(LOTTO_NUMBERS);
        List<LottoNumber> lottoNumbers = new ArrayList<>(LOTTO_NUMBERS.subList(0, LOTTO_SIZE));
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }
}
