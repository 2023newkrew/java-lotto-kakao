package lotto.model;

import java.util.Objects;

public class LottoValue {
    private final int value;

    public LottoValue(int value) {
        if (value < 1 || value > 45) {
            throw new IllegalArgumentException();
        }
        this.value = value;
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
