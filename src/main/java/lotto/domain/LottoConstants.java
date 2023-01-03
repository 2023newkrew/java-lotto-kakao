package lotto.domain;

import static lotto.domain.LottoRank.*;

public enum LottoConstants {
    LOTTO_NUMBER_LOWER_BOUND(1),
    LOTTO_NUMBER_UPPER_BOUND(45),
    LOTTO_NUMBER_COUNT(6),
    LOTTO_MIN_COUNT(0),
    LOTTO_PRICE(1000);

    private final int value;

    LottoConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }




}
