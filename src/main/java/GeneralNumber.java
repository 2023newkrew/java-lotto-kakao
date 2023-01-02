import util.validator.GeneralNumberValidator;

public class GeneralNumber {
    private final int number;
    public GeneralNumber(String input) {
        GeneralNumberValidator.validate(input);
        number = Integer.parseInt(input);
    }

}
