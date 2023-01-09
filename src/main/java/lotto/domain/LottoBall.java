package lotto.domain;

import java.util.Objects;

public class LottoBall {

    private static final int MINIMUM_VALUE = 1;
    private static final int MAXIMUM_VALUE = 45;

    private final int number;

    public LottoBall(int number) {
        validateNumber(number);
        this.number = number;
    }

    private void validateNumber(int number) {
        if (number < MINIMUM_VALUE || number > MAXIMUM_VALUE) {
            throw new IllegalArgumentException("로또 번호는 1이상, 45 이하여야 합니다.");
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoBall lottoBall = (LottoBall) o;
        return number == lottoBall.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
