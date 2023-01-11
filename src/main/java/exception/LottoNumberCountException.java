/**
 * 로또 번호 개수가 6개가 아닐 경우 발생하는 예외
 */
package exception;

public class LottoNumberCountException extends IllegalArgumentException {
    public LottoNumberCountException() {
        super("로또 번호의 개수는 6개 입니다.");
    }

    public LottoNumberCountException(String message) {
        super(message);
    }
}
