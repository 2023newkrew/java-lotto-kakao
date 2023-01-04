package lotto.model;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    private final int leftOver;
    private final int used;

    public Money(int initial) {
        this.leftOver = initial;
        this.used = 0;
    }

    private Money(int leftOver, int used) {
        this.leftOver = leftOver;
        this.used = used;
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
