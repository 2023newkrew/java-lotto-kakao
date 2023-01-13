package domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.LottoConstant.*;

public class LottoNumbersAutoGenerator implements LottoTicketGenerator{
    public static final List<Integer> NUMBERS;

    static {
        NUMBERS = IntStream.range(LOTTO_NUMBER_MIN_VALUE, LOTTO_NUMBER_MAX_VALUE)
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public LottoNumbers generate() {
        Collections.shuffle(NUMBERS);
        List<LottoNumber> lottoNumbers = NUMBERS.subList(0, LOTTO_NUMBERS_LENGTH)
                .stream().map(LottoNumber::new)
                .collect(Collectors.toList());

        return new LottoNumbers(lottoNumbers);
    }

    @Override
    public List<LottoNumbers> generate(int lottoCount) {
        return IntStream.range(0, lottoCount)
                .mapToObj((currentCount) -> generate())
                .collect(Collectors.toList());
    }
}
