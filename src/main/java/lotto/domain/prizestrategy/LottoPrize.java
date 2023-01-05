package lotto.domain.prizestrategy;

public enum LottoPrize {
    FIRST_PRIZE(2000000000, "6개 일치", new FirstPrizeStrategy()),
    SECOND_PRIZE(30000000, "5개 일치, 보너스 볼 일치", new SecondPrizeStrategy()),
    THIRD_PRIZE(1500000, "5개 일치", new ThirdPrizeStrategy()),
    FOURTH_PRIZE(50000, "4개 일치", new FourthPrizeStrategy()),
    FIFTH_PRIZE(5000, "3개 일치", new FifthPrizeStrategy()),
    NONE(0, "0개 일치", new NoPrizeStrategy());

    private final long prizeMoney;
    private final String description;
    private final PrizeStrategy prizeStrategy;

    LottoPrize(long prizeMoney, String description, PrizeStrategy prizeStrategy) {
        this.prizeMoney = prizeMoney;
        this.description = description;
        this.prizeStrategy = prizeStrategy;
    }

    public long getPrizeMoney() {
        return this.prizeMoney;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isQualified(int matchNumberCount, boolean hasMagicNumber) {
        return this.prizeStrategy.isQualified(matchNumberCount, hasMagicNumber);
    }
}
