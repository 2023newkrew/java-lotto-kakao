package lotto.domain;

import lotto.domain.exception.InvalidLottoCountValue;
import static lotto.domain.constants.LottoConstants.*;
import static lotto.domain.constants.LottoStringForm.Korean.*;

import java.util.Objects;

public class LottoCount {

    private final int count;

    public LottoCount(int number) {
        if (number<0){
            throw new InvalidLottoCountValue();
        }
        this.count = number;
    }

    /**
     * convert cash into count of buyable lotto.
     * @param cash
     */
    public LottoCount(Cash cash){
        this((int)(cash.getCash() / LOTTO_PRICE));
    }

    public int getCount() {
        return count;
    }

    /**
     * @param val you want to add to trial using integer value.
     * @return new LottoCount instance which have added value.
     */
    public LottoCount add(int val){
        return new LottoCount(count+val);
    }

    /**
     * @param val you want to add to trial using LottoCount instance.
     * @return new LottoCount instance which have added value.
     */
    public LottoCount add(LottoCount val){
        return new LottoCount(count+val.count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        LottoCount that = (LottoCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    /**
     * Automatically convert to String value which have count unit.<br>
     * If you want to change {@code COUNT_UNIT}, change value of {@link lotto.domain.constants.LottoStringForm} manually
     * or make another language class and use it.
     * @return cash+{@code COUNT_UNIT}
     */
    @Override
    public String toString() {
        return count+COUNT_UNIT;
    }
}
