package util.validator;

import exception.NegativeArgumentException;

public class CountValidator {

    public static void validate(String input, int totalCount) {
        NumberValidator.validateNumberFormat(input);
        int count = Integer.parseInt(input);
        validatePositive(count);
        validateBudget(count, totalCount);
    }

    private static void validatePositive(int count) {
        if (count < 0) {
            throw new NegativeArgumentException("개수는 항상 양수입니다.");
        }
    }

    private static void validateBudget(int count, int totalCount) {
        if (count > totalCount) {
            throw new IllegalArgumentException("금액이 모자랍니다.");
        }
    }

}
