package lotto;

import lotto.exception.InvalidCashValue;

import java.util.Objects;

@SuppressWarnings("rawtypes")
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
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
        if (Long.class == o.getClass()) {
            return Long.compare(cash, (Long)o);
        }
        if (getClass() != o.getClass()){
            throw new RuntimeException();
        }
        return Long.compare(this.cash, ((Cash)o).getCash());
    }
}
