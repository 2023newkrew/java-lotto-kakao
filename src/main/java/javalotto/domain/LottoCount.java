package javalotto.domain;

import java.util.Objects;

import static javalotto.domain.PurchaseAmount.PURCHASE_AMOUNT_UNIT_PRICE;

public class LottoCount {
    private final int count;

    private LottoCount(int count) {
        this.count = count;
    }

    public static LottoCount of(PurchaseAmount purchaseAmount) {
        return new LottoCount(purchaseAmount.getPurchaseAmount() / PURCHASE_AMOUNT_UNIT_PRICE);
    }

    public static LottoCount withCount(int count) {
        return new LottoCount(count);
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
