package lotto;

import lotto.exception.InvalidLottoBallNumber;

import java.util.Objects;

public class LottoBallNumber implements Comparable{
    private final int num;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 45;

    public LottoBallNumber(int num) {
        if (num < MIN_VALUE || num>MAX_VALUE){
            throw new InvalidLottoBallNumber();
        }
        this.num = num;
    }

    @Override
    public int compareTo(Object o) {
        if (o.getClass() == Integer.class){
            return num-(Integer)o;
        }
        if (o == null || getClass() != o.getClass()){
            throw new RuntimeException();
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
