package lotto.domain;

import java.util.Objects;

public class LottoBall implements Comparable<LottoBall> {

    protected static final int MINIMUM_BALL_NUMBER = 1;
    protected static final int MAXIMUM_BALL_NUMBER = 45;

    private final int number;

    public LottoBall(int number) {
        validateNumber(number);
        this.number = number;
    }

    private void validateNumber(int number) {
        if (number < MINIMUM_BALL_NUMBER || number > MAXIMUM_BALL_NUMBER) {
            throw new IllegalArgumentException(MINIMUM_BALL_NUMBER + "와 " + MAXIMUM_BALL_NUMBER + " 사이의 숫자만을 허용합니다.");
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

    @Override
    public int compareTo(LottoBall o) {
        return this.number - o.number;
    }
}
