package lotto.model;

import lotto.exception.InvalidCashValue;

public class BuyCash extends Cash {
    public BuyCash(Cash cash) {
        this(cash.getCash());
    }

    public BuyCash(long cash) {
        if (cash < LottoConstants.LOTTO_PRICE) {
            throw new InvalidCashValue();
        }

        super.cash = cash;
    }

    @Override
    public BuyCash plus(Cash val2) {
        return this.plus(val2.getCash());
    }

    @Override
    public BuyCash plus(long val2) {
        return new BuyCash(this.cash + val2);
    }
}
