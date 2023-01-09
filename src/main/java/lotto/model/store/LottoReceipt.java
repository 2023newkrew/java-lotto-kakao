package lotto.model.store;

import java.util.Objects;

public class LottoReceipt {

    private final Money money;

    private final Money change;

    public static LottoReceipt from(Money money, Money totalPrice) {
        if (Objects.isNull(money)) {
            money = Money.ZERO;
        }
        Money change = calculateChange(money, totalPrice);

        return new LottoReceipt(money, change);
    }

    private static Money calculateChange(Money payment, Money totalPrice) {
        if (Objects.isNull(totalPrice)) {
            totalPrice = Money.ZERO;
        }

        return payment.subtract(totalPrice);
    }

    private LottoReceipt(Money money, Money change) {
        this.money = money;
        this.change = change;
    }

    public Money getPayment() {
        return money;
    }

    public Money getChange() {
        return change;
    }
}
