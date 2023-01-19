package lotto.domain;

import lotto.domain.prizestrategy.FifthPrizeStrategy;
import lotto.domain.prizestrategy.FirstPrizeStrategy;
import lotto.domain.prizestrategy.FourthPrizeStrategy;
import lotto.domain.prizestrategy.NoPrizeStrategy;
import lotto.domain.prizestrategy.PrizeStrategy;
import lotto.domain.prizestrategy.SecondPrizeStrategy;
import lotto.domain.prizestrategy.ThirdPrizeStrategy;

public enum LottoPrize {
    FIRST_PRIZE(2_000_000_000, "6개 일치", new FirstPrizeStrategy()),
    SECOND_PRIZE(30_000_000, "5개 일치, 보너스 볼 일치", new SecondPrizeStrategy()),
    THIRD_PRIZE(1_500_000, "5개 일치", new ThirdPrizeStrategy()),
    FOURTH_PRIZE(50_000, "4개 일치", new FourthPrizeStrategy()),
    FIFTH_PRIZE(5_000, "3개 일치", new FifthPrizeStrategy()),
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
