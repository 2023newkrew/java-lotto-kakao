package javalotto.exception;

import static javalotto.domain.PurchaseAmount.PURCHASE_AMOUNT_UNIT_PRICE;

public class AmountNotMultipleOfUnitPriceException extends IllegalArgumentException {
    public AmountNotMultipleOfUnitPriceException(int amount) {
        this(String.format("로또 구매 금액은 %d 의 배수여야 합니다. 입력하신 구입 금액: %d",
                PURCHASE_AMOUNT_UNIT_PRICE, amount));
    }

    public AmountNotMultipleOfUnitPriceException(String message) {
        super(message);
    }
}
