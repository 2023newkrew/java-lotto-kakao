package lotto.model.ranking;

import lotto.model.store.LottoReceipt;

public class AnalysisResult {

    private final RankingCounts rankingCounts;

    private final ProfitRate profitRate;

    public static AnalysisResult from(RankingCounts rankingCounts, LottoReceipt receipt) {
        TotalPrize totalPrize = TotalPrize.from(rankingCounts);
        ProfitRate profitRate = ProfitRate.from(receipt, totalPrize.getMoney());

        return new AnalysisResult(rankingCounts, profitRate);
    }

    private AnalysisResult(RankingCounts rankingCounts, ProfitRate profitRate) {
        this.rankingCounts = rankingCounts;
        this.profitRate = profitRate;
    }

    public long countBy(LottoRanking ranking) {
        return rankingCounts.countBy(ranking);
    }

    public double getProfitRate() {
        return profitRate.doubleValue();
    }
}
