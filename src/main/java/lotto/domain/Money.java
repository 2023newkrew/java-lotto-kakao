package lotto.domain;

import java.util.Objects;

public class Money {

    private final int amount;

    public Money(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("돈은 0원 이상이여야 합니다.");
        }
    }

    public boolean isSmallerThan(Money money) {
        return this.amount < money.amount;
    }

    public double divideBy(Money divider) {
        return this.amount / (double) divider.amount;
    }

    public Money multiply(int number) {
        return new Money(amount * number);
    }

    public Money add(Money money) {
        return new Money(amount + money.amount);
    }

    public Money subtract(Money money) {
        return new Money(amount - money.amount);
    }


    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
