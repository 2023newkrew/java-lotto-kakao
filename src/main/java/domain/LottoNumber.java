package domain;

import java.util.Objects;

import static utils.ErrorMessage.OUT_OF_LOTTO_NUMBER_RANGE;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final Integer LOTTO_NUMBER_START = 1;
    private static final Integer LOTTO_NUMBER_END = 45;

    private Integer number;

    public LottoNumber(Integer number) {
        validateLottoNumber(number);
        this.number = number;
    }

    private void validateLottoNumber(Integer number) {
        if (number < LOTTO_NUMBER_START || number > LOTTO_NUMBER_END) {
            throw new IllegalArgumentException(OUT_OF_LOTTO_NUMBER_RANGE.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return number.toString();
    }

    @Override
    public int compareTo(LottoNumber other) {
        return this.number - other.number;
    }
}
