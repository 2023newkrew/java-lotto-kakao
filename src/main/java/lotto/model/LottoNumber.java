package lotto.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;
    private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();
    private final int value;

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber valueOf(int value) {
        if (isOutOfRange(value)) {
            throw new IllegalArgumentException("LottoValue 는 1~45의 정수 값이어야 한다.");
        }
        if (!CACHE.containsKey(value)) CACHE.put(value, new LottoNumber(value));
        return CACHE.get(value);
    }

    private static boolean isOutOfRange(int value) {
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

    @Override
    public String toString() {
        return "LottoNumber{" +
                "value=" + value +
                '}';
    }
}
