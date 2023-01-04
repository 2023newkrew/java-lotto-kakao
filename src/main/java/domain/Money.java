package domain;

import util.validator.MoneyValidator;

public class Money {

    public static final int LOTTO_PRICE = 1000;
    private final int money;

    public Money(String paidPrice) {
        MoneyValidator.validate(paidPrice);
        this.money = Integer.parseInt(paidPrice);
    }

    public int getCount() {
        return money / LOTTO_PRICE;
    }

    public int getMoney() {
        return money;
    }

}
