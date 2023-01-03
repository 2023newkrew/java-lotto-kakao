package lotto.model.enums;

public enum LottoSettings {

    MAX_LENGTH(6),

    MIN_RANGE(1),

    MAX_RANGE(45),

    PRICE(1000),
    ;

    private final Integer value;

    LottoSettings(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
