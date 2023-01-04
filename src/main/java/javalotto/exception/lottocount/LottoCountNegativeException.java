package javalotto.exception.lottocount;

public class LottoCountNegativeException extends IllegalArgumentException {

    public LottoCountNegativeException(int lottoCount) {
        this(String.format("로또 개수는 음수가 될 수 없습니다. 생성된 로또 개수: %d", lottoCount));
    }

    public LottoCountNegativeException(String message) {
        super(message);
    }
}
