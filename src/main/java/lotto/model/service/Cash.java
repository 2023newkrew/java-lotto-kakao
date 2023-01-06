package lotto.model.service;

public class Cash {
    private long amount;

    public Cash(long capital) {
        this.amount = capital;
    }

    public int getPurchasingAbility(long price) {
        return (int) (this.amount / price);
    }

    public void pay(long cost) {
        if (this.amount < cost) {
            throw new IllegalArgumentException("로또를 구매할 현금이 부족합니다.");
        }
        this.amount -= cost;
    }
}
