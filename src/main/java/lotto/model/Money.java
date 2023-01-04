package lotto.model;

public class Money {
    private final int LOTTO_PRICE = 1000;
    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public int getPurchasableCount() {
        return this.money / LOTTO_PRICE;
    }

    public int getUsedMoney() {
        return getPurchasableCount() * LOTTO_PRICE;
    }
}
