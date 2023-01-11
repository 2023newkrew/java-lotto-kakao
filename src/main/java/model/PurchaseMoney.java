package model;

import exception.PurchaseMoneyException;

public class PurchaseMoney extends Money {
    private static final int LOTTO_PRICE = 1000;

    public PurchaseMoney(long money) {
        if (money < LOTTO_PRICE || money % LOTTO_PRICE != 0) {
            throw new PurchaseMoneyException();
        }
        this.money = money;
    }
}
