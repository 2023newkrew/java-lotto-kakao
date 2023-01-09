package javalotto.domain;

import javalotto.exception.lottocount.LottoCountNegativeException;

import java.util.Objects;

public class LottoCount {
    private final int count;

    private LottoCount(int count) {
        this.count = count;
    }

    public static LottoCount of(PurchaseAmount purchaseAmount) {
        int count = purchaseAmount.getPurchaseAmount() / PurchaseAmount.PURCHASE_AMOUNT_UNIT_PRICE;
        validateLottoCount(count);
        return new LottoCount(count);
    }

    public static LottoCount withCount(int count) {
        validateLottoCount(count);
        return new LottoCount(count);
    }

    private static void validateLottoCount(int count) {
        if (count < 0) {
            throw new LottoCountNegativeException(count);
        }
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoCount that = (LottoCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
