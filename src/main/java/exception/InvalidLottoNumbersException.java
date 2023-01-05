package exception;

public class InvalidLottoNumbersException extends IllegalArgumentException {
    public InvalidLottoNumbersException() {
        super("로또 번호는 중복없는 6자리 숫자여야 합니다.");
    }
}
