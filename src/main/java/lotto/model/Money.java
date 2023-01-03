package lotto.model;

public class Money {

    private static final long LOTTO_PRICE = 1000L;

    private final long amount;

    public Money(long amount) {
        if (amount <= 0L) {
            throw new IllegalArgumentException("구매 금액은 0원을 초과해야 합니다.");
        }
        this.amount = amount;
    }

    public long getLottoCount() {
        return amount / LOTTO_PRICE;
    }

    public long getAmount() {
        return amount;
    }

    public long getRemain() {
        return amount - getLottoCount() * LOTTO_PRICE;
    }
}
