package lotto.domain;

import lotto.domain.exception.InvalidLottoBallNumber;

import java.util.Objects;
import static lotto.domain.LottoConstants.*;

public class LottoBallNumber implements Comparable{
    private final int num;

    public LottoBallNumber(int num) {
        if (num < BALLNUMBER_MIN_VALUE ||
                num> BALLNUMBER_MAX_VALUE){
            throw new InvalidLottoBallNumber();
        }
        this.num = num;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null || (getClass() != o.getClass() && o.getClass() != Integer.class)){
            throw new RuntimeException();
        }
        if (o.getClass() == Integer.class){
            return num-(Integer)o;
        }
        return num - ((LottoBallNumber)o).num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        LottoBallNumber that = (LottoBallNumber) o;
        return num == that.num;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }

    @Override
    public String toString() {
        return ""+num;
    }
}
