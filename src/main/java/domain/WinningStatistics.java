package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class WinningStatistics {
    private final List<LottoMatchResult> lottoMatchResults;
    public WinningStatistics(List<LottoMatchResult> lottoMatchResults) {
        this.lottoMatchResults = lottoMatchResults;
    }

    public int getRankCount(LottoRank lottoRank){
        return Collections.frequency(getLottoRanks(), lottoRank);
    }

    private List<LottoRank> getLottoRanks(){
        return lottoMatchResults.stream()
                .map((LottoRank::from))
                .collect(Collectors.toList());
    }

    public double getRateOfReturn(){
        return getProfit() / (LottoConstant.LOTTO_PRICE * lottoMatchResults.size());
    }

    private double getProfit(){
        return getLottoRanks().stream()
                .mapToInt(LottoRank::getPrizeMoney)
                .sum();
    }
}
