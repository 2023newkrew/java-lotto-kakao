package domain;

import exception.NegativeArgumentException;

public class Money {

    public static final int LOTTO_PRICE = 1000;
    private final int money;

    public Money(String paidPrice) {
        validate(paidPrice);
        this.money = Integer.parseInt(paidPrice);
    }

    private static void validate(String paidPrice) {
        validateNumberFormat(paidPrice);
        int money = Integer.parseInt(paidPrice);
        validatePositive(money);
        validateNoChange(money);
    }

    private static void validateNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자가 아닌 문자열이 입력되었습니다.");
        }
    }

    private static void validatePositive(int money) {
        if (money < 0) {
            throw new NegativeArgumentException("금액은 항상 양수입니다.");
        }
    }

    private static void validateNoChange(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("로또는 " + LOTTO_PRICE + "원 단위로 판매합니다.");
        }
    }

    public int getCount() {
        return money / LOTTO_PRICE;
    }

    public int getMoney() {
        return money;
    }

}
