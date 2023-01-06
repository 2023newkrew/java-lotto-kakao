package util.validator;

public class MoneyValidator {
    public static void validate(int paidMoney) {
        validateInRange(paidMoney);
    }

    private static void validateInRange(int paidMoney) {
        if (!(0 <= paidMoney && paidMoney < Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("입력된 금액이 유효하지 않습니다.");
        }
    }
}
