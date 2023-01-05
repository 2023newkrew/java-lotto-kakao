package exception.input;

public class IindivisiblePurchaseBudgetException extends IllegalArgumentException {
    private static final int LOTTO_TICKET_PRICE = 1_000;

    public IindivisiblePurchaseBudgetException() {
        super("로또 가격으로 나누어 떨어지는 금액을 입력해주세요.(" + LOTTO_TICKET_PRICE + "원)");
    }
}
