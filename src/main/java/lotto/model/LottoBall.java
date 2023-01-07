package lotto.model;

import lotto.exception.InvalidLottoBallNumber;

import java.util.Objects;

@SuppressWarnings("rawtypes")
public class LottoBall implements Comparable {
    private final int num;

    public LottoBall(int num) {
        if (num < LottoConstants.BALLNUMBER_MIN_VALUE ||
                num > LottoConstants.BALLNUMBER_MAX_VALUE) {
            throw new InvalidLottoBallNumber();
        }

        this.num = num;
    }

    public int getNum() {
        return num;
    }

    @Override
    public int compareTo(Object o) {
        if (o.getClass() == Integer.class) {
            return num - (Integer)o;
        }
        if (getClass() != o.getClass()) {
            throw new RuntimeException();
        }

        return num - ((LottoBall)o).getNum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return num == ((LottoBall)o).getNum();
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }

    @Override
    public String toString() {
        return "" + num;
    }
}
