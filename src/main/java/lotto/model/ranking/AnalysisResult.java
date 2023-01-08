package lotto.model.ranking;

import lotto.model.store.LottoReceipt;
import lotto.model.store.Money;

public class AnalysisResult {

    private final RankingCounts rankingCounts;

    private final double profitRate;

    public static AnalysisResult from(RankingCounts rankingCounts, LottoReceipt receipt) {
        Money totalPrize = rankingCounts.calculateTotalPrize();
        double profitRate = receipt.calculateProfitRate(totalPrize);

        return new AnalysisResult(rankingCounts, profitRate);
    }

    private AnalysisResult(RankingCounts rankingCounts, double profitRate) {
        this.rankingCounts = rankingCounts;
        this.profitRate = profitRate;
    }

    public long countBy(LottoRanking ranking) {
        return rankingCounts.countBy(ranking);
    }

    public double getProfitRate() {
        return profitRate;
    }
}
