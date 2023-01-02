package util.validator;

public class SingleNumberValidator {
    private static final int LOWER_BOUND = 0;
    private static final int UPPER_BOUND = 45;

    public static void validate(String input) {
        int number;
        validateNumberFormat(input);
        number = Integer.parseInt(input);
        validateInRange(number);
    }

    private static void validateNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자가 아닌 문자열이 입력되었습니다.");
        }
    }

    private static void validateInRange(int inputNumber) {
        if (LOWER_BOUND >= inputNumber || inputNumber > UPPER_BOUND) {
            throw new IllegalArgumentException("1 이상 45 이하의 범위를 벗어났습니다.");
        }
    }
}
