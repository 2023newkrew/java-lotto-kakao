package lotto.domain;

import static lotto.constant.MessageConstant.INVALID_NUMBER_RANGE;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final int number;

    private static final Map<Integer, LottoNumber> CACHED_LOTTO_NUMBER = IntStream
            .range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER + 1)
            .boxed()
            .collect(Collectors.toMap(Function.identity(), LottoNumber::new));

    static public LottoNumber from(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(INVALID_NUMBER_RANGE);
        }
        return CACHED_LOTTO_NUMBER.get(number);
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public LottoNumber getNumber() {
        return CACHED_LOTTO_NUMBER.get(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
