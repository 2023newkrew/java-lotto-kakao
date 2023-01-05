package exception.input;

public class InvalidManualPurchaseAmountException extends IllegalArgumentException {
    public InvalidManualPurchaseAmountException(int maxAmount) {
        super(String.format("0 이상 %d 이하의 수를 입력해주세요.", maxAmount));
    }
}
