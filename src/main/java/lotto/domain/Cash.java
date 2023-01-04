package lotto.domain;

import lotto.domain.exception.InvalidCashValue;

import java.util.Objects;

public class Cash implements Comparable {
    private final long cash;
    public Cash(long cash){
        if (cash < 0) {
            throw new InvalidCashValue();
        }
        this.cash = cash;
    }

    public Cash plus(Cash val2) {
        return new Cash(this.cash+val2.getCash());
    }
    public Cash plus(long val2) {
        return new Cash(this.cash+val2);
    }

    public long getCash() {
        return cash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || (getClass() != o.getClass() && o.getClass()!=Long.class)){
            throw new RuntimeException();
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
        return cash+"원";
    }

    @Override
    public int compareTo(Object o) {
        if (o == null || (getClass() != o.getClass() && o.getClass()!=Long.class)){
            throw new RuntimeException();
        }
        if (Long.class == o.getClass()){
            return cash > ((Long)o) ? 1 : (cash == (Long)o ? 0 : -1);
        }
        long otherCash = ((Cash)o).cash;
        return Long.compare(cash, otherCash);
    }
}
