package lotto.model;

public class Money {
    public static final int LOTTO_PRICE = 1000;
    private final int leftOver;
    private final int used;

    public Money(int initial) {
        validateRange(initial);
        this.leftOver = initial;
        this.used = 0;
    }

    private Money(int leftOver, int used) {
        validateRange(leftOver);
        validateRange(used);
        this.leftOver = leftOver;
        this.used = used;
    }

    private void validateRange(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("금액은 0 보다 큰 정수여야 합니다");
        }
    }

    public Money buyLottoTicketsAsManyAs(int count) {
        int used = LOTTO_PRICE * count;
        return new Money(leftOver - used, this.used + used);
    }

    public Money buyLottoTicketsConsumingAllLeftOver() {
        int used = LOTTO_PRICE * getPurchasableCount();
        return new Money(leftOver - used, this.used + used);
    }

    public int getPurchasableCount() {
        return leftOver / LOTTO_PRICE;
    }

    public int getUsedMoney() {
        return used;
    }
}
