package lotto.model.errors;

public class LottoOutOfRangeException extends LottoException {

    public LottoOutOfRangeException() {
        super();
    }

    public LottoOutOfRangeException(String message) {
        super(message);
    }

    public LottoOutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public LottoOutOfRangeException(Throwable cause) {
        super(cause);
    }
}
