package lotto;

public enum WinningCondition {
    FIRST_PRIZE(new LottoResult(6,false), new Cash(2_000_000_000L)),
    SECOND_PRIZE(new LottoResult(5,true), new Cash(30_000_000L)),
    THIRD_PRIZE(new LottoResult(5,false), new Cash(1_500_000L)),
    FOURTH_PRIZE(new LottoResult(4,false), new Cash(50_000L)),
    FIFTH_PRIZE(new LottoResult(3,false), new Cash(5_000L));

    private final LottoResult lottoResult;
    private final Cash winningPrize;

    WinningCondition(LottoResult lottoResult, Cash winningPrize){
        this.lottoResult = lottoResult;
        this.winningPrize = winningPrize;
    }

    public Cash getPrizeIfMatch(LottoResult lottoResult){
        if (lottoResult.equals(this.lottoResult)){
            return winningPrize;
        }
        return new Cash(0L);
    }
}
