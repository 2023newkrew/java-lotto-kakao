package lotto.model;

import java.util.Comparator;
import java.util.Objects;

public class LottoValue implements Comparator<LottoValue> {
    private final static int MIN_VALUE = 1;
    private final static int MAX_VALUE = 45;

    private final int value;

    public LottoValue() {
        value = 1;
    }

    public LottoValue(int value) {
        if (isOutOfRange(value)) {
            throw new IllegalArgumentException("LottoValue 는 1~45의 정수 값이어야 한다.");
        }
        this.value = value;
    }

    private boolean isOutOfRange(int value) {
        return value < MIN_VALUE || value > MAX_VALUE;
    }

    @Override
    public int compare(LottoValue o1, LottoValue o2) {
        return o1.value - o2.value;
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

    public int getValue() {
        return value;
    }
}
