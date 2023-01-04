package buyer;

import java.math.BigDecimal;

public class Money implements Comparable<Money>{
    private final BigDecimal money;

    private Money(int number) {
        this(new BigDecimal(number));
    }

    private Money(BigDecimal number) {
        validateNumber(number);
        this.money = number;
    }

    public static Money valueOf(int number) {
        return new Money(number);
    }

    private void validateNumber(BigDecimal number) {
        if (number.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("금액은 항상 0 이상이어야 합니다");
        }
    }

    public Money decreaseMoney(Money price) {
        if (price.compareTo(this) > 0) {
            throw new RuntimeException("충분한 돈이 없습니다!");
        }
        return new Money(this.money.subtract(price.money));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Money)) return false;

        Money cp = (Money) obj;

        return this.money.equals(cp.money);
    }

    @Override
    public int compareTo(Money o) {
        return this.money.compareTo(o.money);
    }
}
