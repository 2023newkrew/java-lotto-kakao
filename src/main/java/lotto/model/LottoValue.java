package lotto.model;

import java.util.Objects;

public class LottoValue {
    private final int value;

    public LottoValue(int value) {
        if (isOutOfRange(value)) {
            throw new IllegalArgumentException("LottoValue는 1~45의 정수 값이어야 한다.");
        }
        this.value = value;
    }

    private boolean isOutOfRange(int value) {
        return value < 1 || value > 45;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoValue that = (LottoValue) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
