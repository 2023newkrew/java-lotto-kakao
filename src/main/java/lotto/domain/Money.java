package lotto.domain;

public class Money {

    public final static int LOTTO_PRICE = 1000;
    private final int money;

    public Money(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException("로또 한 장의 가격보다 커야 합니다.");
        }
        this.money = money;
    }

    public int getLottoAmount() {
        return this.money / LOTTO_PRICE;
    }
}
