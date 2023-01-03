package lotto.domain;

public enum LottoPrize {
    FIRST_PRIZE(2000000000),
    SECOND_PRIZE(30000000),
    THIRD_PRIZE(1500000),
    FOURTH_PRIZE(50000),
    FIFTH_PRIZE(5000),
    NONE(0);

    private final int prizeMoney;

    LottoPrize(int prizeMoney) {
        this.prizeMoney = prizeMoney;
    }

    public int getPrizeMoney() {
        return this.prizeMoney;
    }
}
