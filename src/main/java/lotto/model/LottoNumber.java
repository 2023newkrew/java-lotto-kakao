package lotto.model;

import lotto.model.errors.LottoOutOfRangeException;

import java.util.Comparator;
import java.util.Objects;

public final class LottoNumber implements Comparator<LottoNumber>, Comparable<LottoNumber> {

    public static final Integer MIN_RANGE = 1;

    public static final Integer MAX_RANGE = 45;

    private final Integer number;

    public LottoNumber(Integer number) {
        this.number = number;
        validateNumber();
    }

    private void validateNumber() {
        if (!isOnRange()) {
            throw new LottoOutOfRangeException(String.format("로또 숫자 범위는 %d 이상 %d 이하여야 합니다.", MIN_RANGE, MAX_RANGE));
        }
    }

    private Boolean isOnRange() {
        return number != null
                && number >= MIN_RANGE
                && number <= MAX_RANGE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoNumber)) return false;
        LottoNumber that = (LottoNumber) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compare(LottoNumber o1, LottoNumber o2) {
        return o1.number - o2.number;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return number - o.number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
