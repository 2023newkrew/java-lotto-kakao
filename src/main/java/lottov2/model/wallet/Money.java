package lottov2.model.wallet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {

    public static Money ZERO = Money.valueOf(BigDecimal.ZERO);

    private final BigDecimal value;

    public static Money valueOf(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("돈은 0원 이상입니다.");
        }

        return new Money(value);
    }

    public static Money valueOf(long value) {
        return valueOf(BigDecimal.valueOf(value));
    }

    private Money(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal bigDecimal() {
        return value;
    }

    public Money add(Money other) {
        return valueOf(value.add(other.value));
    }

    public Money subtract(Money other) {
        return valueOf(value.subtract(other.value));
    }

    public Money multiply(BigDecimal multiplicand) {
        return valueOf(value.multiply(multiplicand));
    }

    public BigDecimal divide(Money other) {
        if (other.equals(Money.ZERO)) {
            throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
        }

        return value.divide(other.value, RoundingMode.DOWN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money)) {
            return false;
        }

        Money money = (Money) o;

        return value.equals(money.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return Objects.toString(value);
    }
}
