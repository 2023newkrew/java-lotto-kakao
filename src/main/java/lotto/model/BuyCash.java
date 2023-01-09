package lotto.model;

import lotto.exception.InvalidCashValue;

public class BuyCash extends Cash {
    public BuyCash(Cash cash) { this(cash.getCash()); }

    public BuyCash(long cash) {
        super(cash);

        if (cash < LottoConstants.LOTTO_PRICE) {
            throw new InvalidCashValue();
        }
    }
}
