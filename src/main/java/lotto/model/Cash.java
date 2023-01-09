package lotto.model;

import java.util.Objects;

@SuppressWarnings("rawtypes")
public class Cash implements Comparable {
    protected long cash;

    protected Cash(long cash) {
        this.cash = cash;
    }

    public Cash plus(Cash val2) {
        return this.plus(val2.getCash());
    }
    public Cash plus(long val2) {
        return new Cash(this.cash + val2);
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

        return cash == ((Cash)o).getCash();
    }

    @Override
    public int hashCode() {
        return Objects.hash(cash);
    }

    @Override
    public int compareTo(Object o) {
        if (Long.class == o.getClass()) {
            return Long.compare(cash, (Long)o);
        }
        if (getClass() != o.getClass()) {
            throw new RuntimeException();
        }

        return Long.compare(this.cash, ((Cash)o).getCash());
    }
}
