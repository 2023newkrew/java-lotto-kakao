package javalotto.domain;

import java.util.Objects;

public class LottoCount {
    private final int count;

    private LottoCount(int count) {
        this.count = count;
    }

    public static LottoCount of(PurchaseAmount purchaseAmount, int unitPrice) {
        return new LottoCount(purchaseAmount.getPurchaseAmount() / unitPrice);
    }

    public static LottoCount withCount(int count) {
        return new LottoCount(count);
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
