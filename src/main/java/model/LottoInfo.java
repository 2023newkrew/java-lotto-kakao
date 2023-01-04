package model;

public enum LottoInfo {
    LOTTO_NUMBER_COUNT(6),
    LOTTO_PRICE(1000),
    MIN_NUMBER(1),
    MAX_NUMBER(45);
    int value;

    LottoInfo(int value) {
        this.value = value;
    }

    public int valueOf() {
        return value;
    }
}

