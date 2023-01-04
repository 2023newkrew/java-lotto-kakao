package lotto;

import java.util.Objects;

public class LottoBall implements Comparable<LottoBall> {
    private final int ball;

    public LottoBall(int ball) {
        if (ball <= 0 || ball > 45) {
            throw new IllegalArgumentException("ball 은 1 ~ 45 사이의 숫자여야 합니다.");
        }
        this.ball = ball;
    }

    @Override
    public String toString() {
        return "" + ball;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoBall lottoBall = (LottoBall) o;
        return ball == lottoBall.ball;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ball);
    }

    @Override
    public int compareTo(LottoBall o) {
        return ball - o.ball;
    }
}
