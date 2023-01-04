package domain.lotto;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    public static final int MINIMUM_VALUE = 1;
    public static final int MAXIMUM_VALUE = 45;

    private final int value;

    public LottoNumber(int value) {
        validateRange(value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private void validateRange(int value) {
        if (value < MINIMUM_VALUE || value > MAXIMUM_VALUE) {
            throw new IllegalArgumentException("잘못된 범위의 숫자입니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(value, o.value);
    }
}
