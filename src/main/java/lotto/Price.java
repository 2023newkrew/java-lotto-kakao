package lotto;

import java.util.Objects;

public class Price {
    private final long value;

    public Price(long value) {
        this.value = value;
    }

    public Price(Price other) {
        this.value = other.value;
    }

    public Price add(Number other) {
        return new Price(this.value + other.longValue());
    }

    public Price add(Price other) {
        return new Price(this.value + other.value);
    }

    @Override
    public String toString() {
        return "Price{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return value == price.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public float scale(Price outcome) {
        return ((float) outcome.value) / this.value;
    }

    public Price multiply(int other) {
        return new Price(value * other);
    }

    public Price multiply(long other) {
        return new Price(value * other);
    }

    public long floorDivide(Price other) {

        return this.value / other.value;
    }
}
