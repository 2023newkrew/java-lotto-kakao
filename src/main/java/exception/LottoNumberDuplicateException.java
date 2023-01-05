/**
 * 로또 번호에 중복이 있을 경우 발생하는 예외
 */
package exception;

public class LottoNumberDuplicateException extends RuntimeException {
    public LottoNumberDuplicateException() {
        super("로또 번호에 중복이 없어야 합니다.");
    }

    public LottoNumberDuplicateException(String message) {
        super(message);
    }
}
