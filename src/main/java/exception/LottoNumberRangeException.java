/**
 * 로또 번호가 1~45 범위를 벗어날 경우 발생하는 예외
 */
package exception;

public class LottoNumberRangeException extends IllegalArgumentException {
    public LottoNumberRangeException() {
        super("로또 번호는 1이상 45 이하입니다.");
    }

    public LottoNumberRangeException(String message) {
        super(message);
    }
}
