package util.validator;

import java.util.Arrays;

public class WinningNumbersValidator {

    public static final String DELIMITER = ",";
    public static final int VALID_LENGTH = 6;

    public static void validate(String input) {
        String[] splitInput = input.split(DELIMITER);
        validateLength(splitInput);

    }

    private static void validateLength(String[] inputs) {
        if (inputs.length != VALID_LENGTH) {
            throw new IllegalArgumentException("길이");
        }
    }



}
