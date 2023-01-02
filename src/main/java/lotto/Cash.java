package lotto;

import lotto.exception.InvalidCashValue;

public class Cash {
    public Cash(int cash){
        throw new InvalidCashValue();
    }
}
