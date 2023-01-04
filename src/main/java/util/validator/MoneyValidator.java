package util.validator;

import domain.Money;
import exception.NegativeArgumentException;

public class MoneyValidator {

    public static void validate(String paidPrice) {
        NumberValidator.validateNumberFormat(paidPrice);
        int money = Integer.parseInt(paidPrice);
        validatePositive(money);
        validateNoChange(money);
    }

    private static void validatePositive(int money) {
        if (money < 0) {
            throw new NegativeArgumentException("금액은 항상 양수입니다.");
        }
    }

    private static void validateNoChange(int money) {
        if (money % Money.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("로또는 " + Money.LOTTO_PRICE + "원 단위로 판매합니다.");
        }
    }

}
