package lotto.common;

public enum LottoConfiguration {
    MIN_VALUE(1), MAX_VALUE(45), LOTTO_COUNT(6);

    private final Integer value;

    LottoConfiguration(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}