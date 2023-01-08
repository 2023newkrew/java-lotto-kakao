package lotto.model.store;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class LottoReceipt {

    private final Money totalPrice;

    private final Money change;

    public static LottoReceipt from(Money money, Money totalPrice) {
        if (Objects.isNull(money)) {
            money = Money.ZERO;
        }
        if (Objects.isNull(totalPrice)) {
            totalPrice = Money.ZERO;
        }

        return new LottoReceipt(totalPrice, money.subtract(totalPrice));
    }

    private LottoReceipt(Money totalPrice, Money change) {
        this.totalPrice = totalPrice;
        this.change = change;
    }

    public Money getChange() {
        return change;
    }

    public double calculateProfitRate(Money totalPrize) {
        Money totalProfit = change.add(totalPrize);
        Money base = change.add(totalPrice);
        BigDecimal profitRate = totalProfit.divide(base, 2, RoundingMode.DOWN);

        return profitRate.doubleValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoReceipt)) {
            return false;
        }

        LottoReceipt receipt = (LottoReceipt) o;

        if (!totalPrice.equals(receipt.totalPrice)) {
            return false;
        }

        return getChange().equals(receipt.getChange());
    }

    @Override
    public int hashCode() {
        int result = totalPrice.hashCode();
        result = 31 * result + getChange().hashCode();
        return result;
    }
}
