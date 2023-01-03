package lotto;

public enum WinningCondition {
    FIRST_PRIZE(new LottoResult(LottoConstants.BALLCOUNT_LIMIT, false),
            new Cash(LottoConstants.FIRST_PRIZE_CASH), 4),
    SECOND_PRIZE(new LottoResult(LottoConstants.BALLCOUNT_LIMIT-1, true),
            new Cash(LottoConstants.SECOND_PRIZE_CASH), 3),
    THIRD_PRIZE(new LottoResult(LottoConstants.BALLCOUNT_LIMIT-1, false),
            new Cash(LottoConstants.THIRD_PRIZE_CASH), 2),
    FOURTH_PRIZE(new LottoResult(LottoConstants.BALLCOUNT_LIMIT-2, false),
            new Cash(LottoConstants.FOURTH_PRIZE_CASH), 1),
    FIFTH_PRIZE(new LottoResult(LottoConstants.BALLCOUNT_LIMIT-3, false),
            new Cash(LottoConstants.FIFTH_PRIZE_CASH), 0);

    private final LottoResult lottoResult;
    private final Cash winningPrize;
    private final int printOrder;

    WinningCondition(LottoResult lottoResult, Cash winningPrize, int printOrder) {
        this.lottoResult = lottoResult;
        this.winningPrize = winningPrize;
        this.printOrder = printOrder;
    }

    public Cash getPrizeIfMatch(LottoResult lottoResult) {
        if (lottoResult.equals(this.lottoResult)) {
            return winningPrize;
        }

        return new Cash(0L);
    }

    public int getOrder() {
        return printOrder;
    }

    public LottoResult getLottoResult() {
        return lottoResult;
    }

    @Override
    public String toString() {
        return lottoResult + "(" + winningPrize + ")";
    }
}
