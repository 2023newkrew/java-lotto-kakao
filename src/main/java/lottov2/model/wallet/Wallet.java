package lottov2.model.wallet;

import java.math.BigDecimal;
import java.util.Objects;

public class Wallet {

    private Money balance;

    public static Wallet create(Money money) {
        if (Objects.isNull(money)) {
            money = Money.ZERO;
        }

        return new Wallet(money);
    }

    private Wallet(Money baseMoney) {
        this.balance = baseMoney;
    }

    public Money getBalance() {
        return balance;
    }

    public void withdraw(Money money) {
        try {
            balance = balance.subtract(money);
        } catch (IllegalArgumentException ignore) {
            throw new IllegalArgumentException("잔액보다 큰 금액은 인출할 수 없습니다.");
        }
    }
}
