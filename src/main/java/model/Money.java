package model;

import exception.PurchaseMoneyException;

public class Money {
    private final long purchaseMoney;

    public Money(long purchaseMoney) {
        if (purchaseMoney < 1000 || purchaseMoney % 1000 != 0) {
            throw new PurchaseMoneyException();
        }
        this.purchaseMoney = purchaseMoney;
    }

    long getPurchaseMoney() {
        return purchaseMoney;
    }

}
