package lotto.model;

import lotto.exception.InvalidLottoBallNumber;

import java.util.Objects;

@SuppressWarnings("rawtypes")
public class LottoBall implements Comparable {
    private final int number;

    public LottoBall(int number) {
        if (number < LottoConstants.BALLNUMBER_MIN_VALUE ||
                number > LottoConstants.BALLNUMBER_MAX_VALUE) {
            throw new InvalidLottoBallNumber();
        }

        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(Object o) {
        if (o.getClass() == Integer.class) {
            return number - (Integer)o;
        }
        if (getClass() != o.getClass()) {
            throw new RuntimeException();
        }

        return number - ((LottoBall)o).getNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return number == ((LottoBall)o).getNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "" + number;
    }
}
