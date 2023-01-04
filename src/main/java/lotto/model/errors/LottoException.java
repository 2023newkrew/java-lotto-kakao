package lotto.model.errors;

public class LottoException extends RuntimeException {

    public LottoException() {
        super();
    }

    public LottoException(String message) {
        super(message);
    }

    public LottoException(String message, Throwable cause) {
        super(message, cause);
    }

    public LottoException(Throwable cause) {
        super(cause);
    }
}
