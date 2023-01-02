package domain;

import util.validator.SingleNumberValidator;

public class GeneralNumber {
    private final int number;
    public GeneralNumber(String input) {
        SingleNumberValidator.validate(input);
        number = Integer.parseInt(input);
    }

}
