package lotto.model.errors;

public class LottoDuplicatedNumberException extends LottoException {

    public LottoDuplicatedNumberException() {
        super();
    }

    public LottoDuplicatedNumberException(String message) {
        super(message);
    }

    public LottoDuplicatedNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public LottoDuplicatedNumberException(Throwable cause) {
        super(cause);
    }
}
