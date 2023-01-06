/**
 * 구입 금액이 1000의 배수가 아닐 경우 발생하는 예외
 */
package exception;

public class PurchaseMoneyException extends RuntimeException {
    public PurchaseMoneyException() {
        super("로또는 1장에 1000원 입니다. 구입 금액은 1000원의 배수여야 합니다.");
    }

    public PurchaseMoneyException(String message) {
        super(message);
    }
}
