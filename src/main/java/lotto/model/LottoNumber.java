package lotto.model;

import java.util.Objects;

public class LottoNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private final int value;

    public LottoNumber(int value) {
        if (isOutOfRange(value)) {
            throw new IllegalArgumentException("LottoValue 는 1~45의 정수 값이어야 한다.");
        }
        this.value = value;
    }

    private boolean isOutOfRange(int value) {
        return value < MIN_NUMBER || value > MAX_NUMBER;
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

    public int getValue() {
        return value;
    }
}
