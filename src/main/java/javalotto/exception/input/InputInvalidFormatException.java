package javalotto.exception.input;

public class InputInvalidFormatException extends IllegalArgumentException {
    public InputInvalidFormatException(String playerInput, Throwable cause) {
        super("잘못된 입력입니다. 사용자의 입력: " + playerInput, cause);
    }
}
