package domain;

import common.constant.Constants;

public class Money {

    private final int money;

    public Money(String paidPrice) {
        validate(paidPrice);
        this.money = Integer.parseInt(paidPrice);
    }

    private void validate(String paidPrice) {
        int money;
        try {
            money = Integer.parseInt(paidPrice);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자를 입력해주세요.");
        }
        if (money < 0) {
            throw new IllegalArgumentException("금액은 항상 양수입니다.");
        }
        if (money % Constants.PRICE != 0) {
            throw new IllegalArgumentException("로또는 " + Constants.PRICE + "원 단위로 판매합니다.");
        }
    }

    public int getCount() {
        return money / Constants.PRICE;
    }

    public int getMoney() {
        return money;
    }

}
