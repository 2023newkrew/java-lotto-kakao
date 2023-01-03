package lotto.domain;

public class LottoNumber {
    public static final int MINIMUM_BOUNDARY = 1;
    public static final int MAXIMUM_BOUNDARY = 45;

    private final int number;

    public LottoNumber(int number) {
        if (number < MINIMUM_BOUNDARY || number > MAXIMUM_BOUNDARY) {
            throw new RuntimeException();
        }
        this.number = number;
    }

    @Override
    public int hashCode() {
        return this.number;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof LottoNumber)) return false;
        return this.number == ((LottoNumber) obj).number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    public int compare(LottoNumber other) {
        return number - other.number;
    }
}
