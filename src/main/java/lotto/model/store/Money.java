package lotto.model.store;

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
        if (isNullOrZero(other)) {
            return this;
        }

        return valueOf(value.add(other.value));
    }

    public Money subtract(Money other) {
        if (isNullOrZero(other)) {
            return this;
        }

        return valueOf(value.subtract(other.value));
    }

    public static boolean isNullOrZero(Money money) {
        return Objects.isNull(money) || money.equals(ZERO);
    }

    public Money multiply(long multiplicand) {
        return valueOf(value.multiply(BigDecimal.valueOf(multiplicand)));
    }

    public double divide(Money other) {
        return divide(other, 0);
    }

    public double divide(Money other, int scale) {
        if (isNullOrZero(other)) {
            throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
        }

        return value.divide(other.value, scale, RoundingMode.DOWN).doubleValue();
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
