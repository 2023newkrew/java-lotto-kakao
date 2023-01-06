/**
 * 수동으로 구입하려는 로또의 개수가 전체 구입 개수보다 많거나 음수로 입력되었을 때 발생하는 예외
 */
package exception;

public class ManualLottoCountException extends RuntimeException {
    public ManualLottoCountException() {
        super("수동으로 구매하는 로또의 개수는 0이상 전체 구매 개수 이하여야 합니다.");
    }


    public ManualLottoCountException(String message) {
        super(message);
    }
}
