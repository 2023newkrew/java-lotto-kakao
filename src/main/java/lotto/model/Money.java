package lotto.model;

public class Money {

    private final long value;

    public static Money valueOf(long value) {
        if (value <= 0L) {
            throw new IllegalArgumentException("돈은 0원을 초과해야 합니다.");
        }

        return new Money(value);
    }

    private Money(long value) {
        this.value = value;
    }

    public long longValue() {
        return value;
    }

    public long getPurchasableCount(Money price) {
        return value / price.value;
    }
}
