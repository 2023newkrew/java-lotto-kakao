package lotto.domain;

public enum LottoPrize {
    FIRST_PRIZE(2000000000, "6개 일치"),
    SECOND_PRIZE(30000000, "5개 일치, 보너스 볼 일치"),
    THIRD_PRIZE(1500000, "5개 일치"),
    FOURTH_PRIZE(50000, "4개 일치"),
    FIFTH_PRIZE(5000, "3개 일치"),
    NONE(0, "0개 일치");

    private final long prizeMoney;
    private final String description;

    LottoPrize(long prizeMoney, String description) {
        this.prizeMoney = prizeMoney;
        this.description = description;
    }


    public long getPrizeMoney() {
        return this.prizeMoney;
    }

    public String getDescription() {
        return this.description;
    }
}
