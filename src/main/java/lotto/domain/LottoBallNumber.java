package lotto.domain;

import lotto.domain.exception.InvalidLottoBallNumber;
import lotto.domain.exception.TypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static lotto.domain.constants.LottoConstants.*;

/**
 * An object that contains number which represent one lotto ball.<br>
 * Assert that number is between {@code BALLNUMBER_MIN_VALUE} and {@code BALLNUMBER_MAX_VALUE}<br>
 * @author Daniel.tomi
 */
public class LottoBallNumber implements Comparable{

    private final int num;

    private static final Map<Integer, LottoBallNumber> cache = new HashMap<>();

    /**
     * Cannot call constructor at outer class.
     * <br>Call LottoBallNumber.get(num) instead.
     */
    private LottoBallNumber(int num) {
        if (num < BALLNUMBER_MIN_VALUE ||
                num> BALLNUMBER_MAX_VALUE){
            throw new InvalidLottoBallNumber();
        }
        this.num = num;
    }

    /**
     * @param num which you want to get from LottoBallNumber cache.
     * @throws InvalidLottoBallNumber if given parameter is not between
     * {@code BALLNUMBER_MIN_VALUE} and {@code BALLNUMBER_MAX_VALUE}
     */
    public static LottoBallNumber get(int num){
        if (!cache.containsKey(num)){
            cache.put(num, new LottoBallNumber(num));
        }
        return cache.get(num);
    }

    /**
     * @param o the object to be compared.
     * @throws TypeMismatchException if o is neither Integer nor LottoBallNumber
     * @return positive number if num is bigger, 0 when same, negative when smaller.
     */
    @Override
    public int compareTo(Object o) {
        if (o == null || (getClass() != o.getClass() && o.getClass() != Integer.class)){
            throw new TypeMismatchException();
        }
        if (o.getClass() == Integer.class){
            return num-(Integer)o;
        }
        return Integer.compare(num,((LottoBallNumber)o).num);
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
