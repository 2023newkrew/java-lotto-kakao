package exception;

public class NotPositiveNumberException extends IllegalArgumentException {
    public NotPositiveNumberException() {
        super("0보다 큰 수를 입력해주세요.");
    }
}
