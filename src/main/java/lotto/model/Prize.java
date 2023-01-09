package lotto.model;

import lotto.exception.InvalidCashValue;

public class Prize extends Cash {
    public Prize(Cash cash) {
        this(cash.getCash());
    }

    public Prize(long cash) {
        super(cash);

        if (cash < 0) {
            throw new InvalidCashValue();
        }
    }
}
