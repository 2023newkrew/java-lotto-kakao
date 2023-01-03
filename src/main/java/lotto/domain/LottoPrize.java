package lotto.domain;

public enum LottoPrize {
    FIRST_PRIZE(2000000000),
    SECOND_PRIZE(30000000),
    THIRD_PRIZE(1500000),
    FOURTH_PRIZE(50000),
    FIFTH_PRIZE(5000),
    NONE(0);

    private final long prizeMoney;

    LottoPrize(long prizeMoney) {
        this.prizeMoney = prizeMoney;
    }

    public long getPrizeMoney() {
        return this.prizeMoney;
    }
}
