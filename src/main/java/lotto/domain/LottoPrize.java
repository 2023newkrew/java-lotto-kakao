package lotto.domain;

public enum LottoPrize {
    FIRST_PRIZE(2000000000, "6개 일치",
            (matchNumberCount, hasMagicNumber) -> matchNumberCount == LottoNumbers.LOTTO_NUMBER_LENGTH),
    SECOND_PRIZE(30000000, "5개 일치, 보너스 볼 일치",
            (matchNumberCount, hasMagicNumber) -> matchNumberCount == LottoNumbers.LOTTO_NUMBER_LENGTH - 1
                    && hasMagicNumber),
    THIRD_PRIZE(1500000, "5개 일치",
            (matchNumberCount, hasMagicNumber) -> matchNumberCount == LottoNumbers.LOTTO_NUMBER_LENGTH - 1),
    FOURTH_PRIZE(50000, "4개 일치",
            (matchNumberCount, hasMagicNumber) -> matchNumberCount == LottoNumbers.LOTTO_NUMBER_LENGTH - 2),
    FIFTH_PRIZE(5000, "3개 일치",
            (matchNumberCount, hasMagicNumber) -> matchNumberCount == LottoNumbers.LOTTO_NUMBER_LENGTH - 3),
    NONE(0, "0개 일치",
            (matchNumberCount, hasMagicNumber) -> matchNumberCount < LottoNumbers.LOTTO_NUMBER_LENGTH - 3);

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

    public boolean isWon(int matchNumberCount, boolean hasMagicNumber) {
        return this.prizeStrategy.isWon(matchNumberCount, hasMagicNumber);
    }
}
