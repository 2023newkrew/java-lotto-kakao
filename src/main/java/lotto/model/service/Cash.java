package lotto.model.service;

public class Cash {
    private long amount;

    public Cash(long amount) {
        this.amount = amount;
    }

    public void pay(Cash cost) {
        if (this.amount < cost.amount) {
            throw new IllegalArgumentException("로또를 구매할 현금이 부족합니다.");
        }
        this.amount -= cost.amount;
    }

    public long checkCapacity(Cash cost) {
        return this.amount / cost.amount;
    }

}
