package exception.input;

public class NotPositivePurchaseBudgetException extends IllegalArgumentException {
    private static final int LOTTO_TICKET_PRICE = 1_000;

    public NotPositivePurchaseBudgetException() {
        super("0 보다 큰 구입 금액을 입력해주세요. (" + LOTTO_TICKET_PRICE + "원 단위)");
    }
}
