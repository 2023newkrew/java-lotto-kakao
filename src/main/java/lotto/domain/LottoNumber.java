package lotto.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;
    private static final Map<Integer, LottoNumber> LOTTO_NUMBER_MAP = new HashMap<>();

    private final int number;

    static {
        IntStream.rangeClosed(MINIMUM_NUMBER, MAXIMUM_NUMBER)
                .forEach(number -> LOTTO_NUMBER_MAP.put(number, new LottoNumber(number)));
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber of(int number) {
        validateNumber(number);
        return LOTTO_NUMBER_MAP.get(number);
    }

    private static void validateNumber(int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException(
                    String.format("%d부터 %d 사이의 숫자를 입력해주세요.", MINIMUM_NUMBER, MAXIMUM_NUMBER));
        }
    }

    public String getString() {
        return String.valueOf(number);
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
    public int compareTo(LottoNumber lottoNumber) {
        return Integer.compare(number, lottoNumber.number);
    }
}
