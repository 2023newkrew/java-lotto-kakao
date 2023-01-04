package lotto.domain;

import static lotto.domain.LottoConstants.MAX_LOTTO_NUMBER;
import static lotto.domain.LottoConstants.MIN_LOTTO_NUMBER;
import static lotto.exception.ExceptionMessage.LOTTO_NUMBER_BOUND_EXCEPTION_MESSAGE;
import static lotto.utils.ErrorMessageFormatter.makeErrorMessage;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {

    private final int num;

    private static final Map<Integer, LottoNumber> CACHED_LOTTO_NUMBERS;

    static {
        CACHED_LOTTO_NUMBERS = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER).boxed()
                .collect(Collectors.toMap(i -> i, LottoNumber::new));
    }

    private LottoNumber(int num) {
        this.num = num;
    }

    public static LottoNumber from(int num) {
        validate(num);
        return CACHED_LOTTO_NUMBERS.get(num);
    }

    private static void validate(int num) {
        if (num < MIN_LOTTO_NUMBER || MAX_LOTTO_NUMBER < num) {
            throw new IllegalArgumentException(makeErrorMessage(LOTTO_NUMBER_BOUND_EXCEPTION_MESSAGE, num, "num"));
        }
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }
}
