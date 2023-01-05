package lotto.domain;

public class LottoNumber {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;

    private final Integer number;

    public LottoNumber(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
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

    public Integer compare(LottoNumber other) {
        return number.compareTo(other.number);
    }
}
