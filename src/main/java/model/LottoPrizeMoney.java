package model;

public enum LottoPrizeMoney {
    FIRST_PRIZE_MONEY(2 * 1000 * 1000 * 1000),
    SECOND_PRIZE_MONEY(30 * 1000 * 1000),
    THIRD_PRIZE_MONEY(1500 * 1000),
    FOURTH_PRIZE_MONEY(50 * 1000),
    FIFTH_PRIZE_MONEY(5 * 1000);
    int value;

    LottoPrizeMoney(int value) {
        this.value = value;
    }

    public int valueOf() {
        return value;
    }
}
