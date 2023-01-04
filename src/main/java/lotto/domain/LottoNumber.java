package lotto.domain;

import static lotto.constant.ExceptionMessages.INVALID_NUMBER_RANGE;

import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber {
    static final int MIN_LOTTO_NUMBER = 1;
    static final int MAX_LOTTO_NUMBER = 45;

    private final int number;
    private static final LottoNumber[] CACHED_LOTTO_NUMBER = new LottoNumber[MAX_LOTTO_NUMBER + 1];

    static {
        IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER + 1)
                .forEach(i -> CACHED_LOTTO_NUMBER[i] = new LottoNumber(i));
    }

    static public LottoNumber from(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(INVALID_NUMBER_RANGE);
        }
        return CACHED_LOTTO_NUMBER[number];
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
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
