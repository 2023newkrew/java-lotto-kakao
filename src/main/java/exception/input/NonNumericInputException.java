package exception.input;

public class NonNumericInputException extends IllegalArgumentException {
    public NonNumericInputException() {
        super("숫자를 입력해주세요.");
    }
}
