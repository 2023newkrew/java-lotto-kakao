package lotto.domain;

import lotto.config.LottoConfig;

public class LottoNumber implements Comparable<LottoNumber> {

    private final Integer number;

    public LottoNumber(int number) {
        if (number < LottoConfig.MIN_NUMBER || number > LottoConfig.MAX_NUMBER) {
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

    @Override
    public int compareTo(LottoNumber other) {
        return number.compareTo(other.number);
    }
}
