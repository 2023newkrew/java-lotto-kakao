package lotto.model.store;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class LottoReceipt {

    private final Money base;

    private final Money balance;

    public static LottoReceipt from(Money base, Money totalPrice) {
        if (Objects.isNull(base)) {
            base = Money.ZERO;
        }
        if (Objects.isNull(totalPrice)) {
            totalPrice = Money.ZERO;
        }

        return new LottoReceipt(base, base.subtract(totalPrice));
    }

    private LottoReceipt(Money base, Money balance) {
        this.base = base;
        this.balance = balance;
    }

    public Money getBalance() {
        return balance;
    }

    public BigDecimal calculateProfitRate(Money totalPrize) {
        return balance.add(totalPrize).divide(base, 2, RoundingMode.DOWN);
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

        if (!base.equals(receipt.base)) {
            return false;
        }
        return balance.equals(receipt.balance);
    }

    @Override
    public int hashCode() {
        int result = base.hashCode();
        result = 31 * result + balance.hashCode();
        return result;
    }
}
