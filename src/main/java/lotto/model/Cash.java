package lotto.model;

import java.util.Objects;

@SuppressWarnings("rawtypes")
public abstract class Cash implements Comparable {
    protected long cash;

    public abstract Cash plus(Cash val2);
    public abstract Cash plus(long val2);

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
