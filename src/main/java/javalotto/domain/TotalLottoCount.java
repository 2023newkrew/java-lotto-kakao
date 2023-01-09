package javalotto.domain;

import java.util.Objects;

import static javalotto.constants.PurchaseAmountConstants.PURCHASE_AMOUNT_UNIT_PRICE;

public class TotalLottoCount {
    private final LottoCount manualLottoCount;
    private final LottoCount autoLottoCount;

    private TotalLottoCount(LottoCount manualLottoCount, LottoCount autoLottoCount) {
        this.manualLottoCount = manualLottoCount;
        this.autoLottoCount = autoLottoCount;
    }

    public static TotalLottoCount of(LottoCount manualLottoCount, PurchaseAmount purchaseAmount) {
        LottoCount autoLottoCount = calculateAutoLottoCount(manualLottoCount, purchaseAmount);

        return new TotalLottoCount(manualLottoCount, autoLottoCount);
    }

    private static LottoCount calculateAutoLottoCount(LottoCount manualLottoCount, PurchaseAmount purchaseAmount) {
        int autoLottoCount =
                (purchaseAmount.getPurchaseAmount() - manualLottoCount.getCount() * PURCHASE_AMOUNT_UNIT_PRICE) / PURCHASE_AMOUNT_UNIT_PRICE;

        return LottoCount.from(autoLottoCount);
    }

    public int getTotalCount() {
        return getManualLottoCount() + getAutoLottoCount();
    }

    public int getManualLottoCount() {
        return manualLottoCount.getCount();
    }

    public int getAutoLottoCount() {
        return autoLottoCount.getCount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalLottoCount that = (TotalLottoCount) o;
        return Objects.equals(manualLottoCount, that.manualLottoCount) && Objects.equals(autoLottoCount, that.autoLottoCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manualLottoCount, autoLottoCount);
    }
}
