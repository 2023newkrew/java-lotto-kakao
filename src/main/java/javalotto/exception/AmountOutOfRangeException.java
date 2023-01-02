package javalotto.exception;

import static javalotto.domain.PurchaseAmount.PURCHASE_AMOUNT_MAX_VALUE;
import static javalotto.domain.PurchaseAmount.PURCHASE_AMOUNT_MIN_VALUE;

public class AmountOutOfRangeException extends IllegalArgumentException {
    public AmountOutOfRangeException(int amount) {
        this(String.format("로또 구매 금액은 %d 이상 %d 이하여야 합니다. 입력하신 구입 금액: %d",
                PURCHASE_AMOUNT_MIN_VALUE, PURCHASE_AMOUNT_MAX_VALUE, amount));
    }

    public AmountOutOfRangeException(String message) {
        super(message);
    }
}
