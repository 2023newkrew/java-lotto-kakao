package javalotto.domain;

import javalotto.exception.purchaseamount.AmountNotMultipleOfUnitPriceException;
import javalotto.exception.purchaseamount.AmountOutOfRangeException;

import java.util.Objects;

public class PurchaseAmount {
    public static final int PURCHASE_AMOUNT_MIN_VALUE = 1000;
    public static final int PURCHASE_AMOUNT_MAX_VALUE = Integer.MAX_VALUE / 1000 * 1000;
    public static final int PURCHASE_AMOUNT_UNIT_PRICE = 1000;

    private final int purchaseAmount;

    private PurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public static PurchaseAmount from(int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);

        return new PurchaseAmount(purchaseAmount);
    }

    private static void validatePurchaseAmount(int purchaseAmount) {
        if (isOutOfRange(purchaseAmount)) {
            throw new AmountOutOfRangeException(purchaseAmount);
        }
        if (isNotMultipleOfUnitPrice(purchaseAmount)) {
            throw new AmountNotMultipleOfUnitPriceException(purchaseAmount);
        }
    }

    private static boolean isOutOfRange(int purchaseAmount) {
        return purchaseAmount < PURCHASE_AMOUNT_MIN_VALUE ||
                purchaseAmount > PURCHASE_AMOUNT_MAX_VALUE;
    }

    private static boolean isNotMultipleOfUnitPrice(int purchaseAmount) {
        return purchaseAmount % PURCHASE_AMOUNT_UNIT_PRICE != 0;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PurchaseAmount that = (PurchaseAmount) o;
        return purchaseAmount == that.purchaseAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseAmount);
    }
}
