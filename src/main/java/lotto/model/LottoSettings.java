package lotto.model;

public enum LottoSettings {
    MAX_LENGTH(6),
    MIN_RANGE(1),
    MAX_RANGE(45),
    ;

    private final Integer value;

    LottoSettings(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
