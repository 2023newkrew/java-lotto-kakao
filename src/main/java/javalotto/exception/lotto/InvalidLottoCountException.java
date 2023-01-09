package javalotto.exception.lotto;


public class InvalidLottoCountException extends IllegalArgumentException {

    public InvalidLottoCountException(int count) {
        this(String.format("적절하지 않은 로또 구매 개수입니다. 주어진 로또 구매 개수: %d", count));
    }

    public InvalidLottoCountException(String message) {
        super(message);
    }
}
