package lotto.domain;
import static lotto.domain.LottoConstants.*;

public enum WinningCondition {
    FIRST_PRIZE(new LottoResult(BALLCOUNT_LIMIT,false),
            new Cash(FIRST_PRIZE_CASH), 4),
    SECOND_PRIZE(new LottoResult(BALLCOUNT_LIMIT-1,true),
            new Cash(SECOND_PRIZE_CASH), 3),
    THIRD_PRIZE(new LottoResult(BALLCOUNT_LIMIT-1,false),
            new Cash(THIRD_PRIZE_CASH), 2),
    FOURTH_PRIZE(new LottoResult(BALLCOUNT_LIMIT-2,false),
            new Cash(FOURTH_PRIZE_CASH), 1),
    FOURTH_PRIZE_BONUS(new LottoResult(BALLCOUNT_LIMIT-2,true),
            new Cash(FOURTH_PRIZE_CASH), 1),
    FIFTH_PRIZE(new LottoResult(BALLCOUNT_LIMIT-3,false),
            new Cash(FIFTH_PRIZE_CASH), 0),
    FIFTH_PRIZE_BONUS(new LottoResult(BALLCOUNT_LIMIT-3,true),
            new Cash(FIFTH_PRIZE_CASH), 0);

    private final LottoResult lottoResult;
    private final Cash winningPrize;
    private final int printOrder;

    WinningCondition(LottoResult lottoResult, Cash winningPrize, int printOrder){
        this.lottoResult = lottoResult;
        this.winningPrize = winningPrize;
        this.printOrder = printOrder;
    }

    public Cash getPrizeIfMatch(LottoResult lottoResult){
        if (lottoResult.equals(this.lottoResult)){
            return winningPrize;
        }
        return new Cash(0L);
    }

    @Override
    public String toString() {
        return lottoResult + "(" + winningPrize + ")";
    }

    public int getOrder() {
        return printOrder;
    }

    public LottoResult getLottoResult() {
        return lottoResult;
    }
}
