package exception;

public class LottoNumberOutOfRangeException extends IllegalArgumentException {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    public LottoNumberOutOfRangeException() {
        super(String.format("로또 번호는 %d 이상 %d 이하의 숫자여야 합니다.", MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
    }
}
