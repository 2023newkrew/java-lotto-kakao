package domain;

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

    public int divideBy(Money divider) {
        return this.amount / divider.amount;
    }
}
