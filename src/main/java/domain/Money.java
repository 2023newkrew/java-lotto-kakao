package domain;

import common.constant.Constants;
import util.validator.MoneyValidator;

public class Money {
    private final int paidMoney;

    public Money(int paidMoney) {
        MoneyValidator.validate(paidMoney);
        this.paidMoney = paidMoney;
    }

    public int getPaidMoney() {
        return paidMoney;
    }

    public int getLottoCount(){
        return paidMoney / Constants.PRICE;
    }
}
