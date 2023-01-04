package buyer;

import java.math.BigDecimal;

public class Money implements Comparable<Money>{
    private BigDecimal money;

    public Money(int money) {
        this.money = new BigDecimal(money);
    }

    public void decreaseMoney(Money price) {
        if (price.compareTo(this) > 0) {
            throw new RuntimeException("충분한 돈이 없습니다!");
        }
        this.money = this.money.subtract(price.money);
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
