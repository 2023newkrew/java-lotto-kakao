package lotto.domain;

import lotto.domain.exception.InvalidCashValue;
import lotto.domain.exception.TypeMismatchException;

import java.util.Objects;

import static lotto.domain.constants.LottoStringForm.Korean.CASH_UNIT;


/**
 * @author Daniel.tomi
*/

public class Cash implements Comparable {
    private final long cash;

    /**
     * makes Cash instance which have {@code (cash)} won.
     * @param cash
     *
     * @author Daniel.kim
     */
    public Cash(long cash){
        if (cash < 0) {
            throw new InvalidCashValue();
        }
        this.cash = cash;
    }

    /**
     * returns new Cash of addition of this.cash, val2.cash
     * if given Cash type instance.
     * @param val2
     * @return new cash
     */
    public Cash plus(Cash val2) {
        return plus(this.cash+val2.getCash());
    }

    /**
     * returns new Cash of addition of this.cash and val2(long)
     * if given long type variable.
     * @param val2
     * @return
     */
    public Cash plus(long val2) {
        return new Cash(this.cash+val2);
    }

    /**
     * returns new Cash of addition of this.cash and val2(long)
     * if given long type variable.
     * @param val2
     * @return
     */
    public Cash minus(Cash val2) {
        return minus(this.cash-val2.cash);
    }
    public Cash minus(long val2) {
        return new Cash(this.cash-val2);
    }

    public long getCash() {
        return cash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || (getClass() != o.getClass() && o.getClass()!=Long.class)){
            return false;
        }
        if (Long.class == o.getClass()){
            return cash == ((Long)o);
        }
        Cash cash1 = (Cash)o;
        return cash == cash1.cash;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cash);
    }

    @Override
    public String toString() {
        return cash+CASH_UNIT;
    }

    /**
     * compare this.cash and o.cash or o if o is type of Cash or Long, .
     * @param o the object to be compared.
     * @throws TypeMismatchException if o is not instance of Long or Cash.
     * @return positive integer value if this.cash is bigger, 0 if same, negative value if smaller.
     */
    @Override
    public int compareTo(Object o) {
        if (o == null || (getClass() != o.getClass() && o.getClass()!=Long.class)){
            throw new TypeMismatchException();
        }
        if (Long.class == o.getClass()){
            return cash > ((Long)o) ? 1 : (cash == (Long)o ? 0 : -1);
        }
        long otherCash = ((Cash)o).cash;
        return Long.compare(cash, otherCash);
    }
}
