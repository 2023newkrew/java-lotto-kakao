package util.validator;

import domain.Lotto;

public class SingleNumberValidator {

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
        if (!(Lotto.MINIMUM <= inputNumber && inputNumber <= Lotto.MAXIMUM)) {
            throw new IllegalArgumentException("1 이상 45 이하의 범위를 벗어났습니다.");
        }
    }
}
