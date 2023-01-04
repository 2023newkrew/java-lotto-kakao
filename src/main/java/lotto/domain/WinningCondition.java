package lotto.domain;
import static lotto.domain.constants.LottoConstants.*;

public enum WinningCondition {
    FIRST_PRIZE(new LottoResults()
            .add(LottoResult.get(ONE_TRIAL_BALL_COUNT,false))
            .setGroupString(LottoResult.get(ONE_TRIAL_BALL_COUNT,false).toString()),
            new Cash(FIRST_PRIZE_CASH), 4),
    SECOND_PRIZE(new LottoResults()
            .add(LottoResult.get(ONE_TRIAL_BALL_COUNT -1,true))
            .setGroupString(LottoResult.get(ONE_TRIAL_BALL_COUNT -1,true).toString()),
            new Cash(SECOND_PRIZE_CASH), 3),
    THIRD_PRIZE(new LottoResults()
            .add(LottoResult.get(ONE_TRIAL_BALL_COUNT -1, false))
            .setGroupString(LottoResult.get(ONE_TRIAL_BALL_COUNT -1, false).toString()),
            new Cash(THIRD_PRIZE_CASH), 2),
    FOURTH_PRIZE(new LottoResults()
            .add(LottoResult.get(ONE_TRIAL_BALL_COUNT -2,true))
            .add(LottoResult.get(ONE_TRIAL_BALL_COUNT -2,false))
            .setGroupString(LottoResult.get(ONE_TRIAL_BALL_COUNT -2, false).toString()),
            new Cash(FOURTH_PRIZE_CASH), 1),
    FIFTH_PRIZE(new LottoResults()
            .add(LottoResult.get(ONE_TRIAL_BALL_COUNT -3,false))
            .add(LottoResult.get(ONE_TRIAL_BALL_COUNT -3,true))
            .setGroupString(LottoResult.get(ONE_TRIAL_BALL_COUNT -3, false).toString()),
            new Cash(FIFTH_PRIZE_CASH), 0);

    private final LottoResults lottoResults;
    private final Cash winningPrize;
    private final int printOrder;

    WinningCondition(LottoResults lottoResults, Cash winningPrize, int printOrder){
        this.lottoResults = lottoResults;
        this.winningPrize = winningPrize;
        this.printOrder = printOrder;
    }

    public Cash getPrizeIfMatch(LottoResult lottoResult){
        if (lottoResults.contains(lottoResult)){
            return winningPrize;
        }
        return new Cash(0L);
    }

    @Override
    public String toString() {
        return lottoResults + " (" + winningPrize + ")";
    }

    public int getOrder() {
        return printOrder;
    }

    public LottoResults getLottoResults() {
        return lottoResults;
    }
}
