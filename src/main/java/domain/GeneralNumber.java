package domain;

import util.validator.SingleNumberValidator;

public class GeneralNumber {
    protected final int number;
    public GeneralNumber(String input) {
        SingleNumberValidator.validate(input);
        number = Integer.parseInt(input);
    }

}
