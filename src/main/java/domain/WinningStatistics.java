package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class WinningStatistics {
    private final List<LottoMatchResult> lottoMatchResults;
    private final int usedMoney;

    public WinningStatistics(GameResult gameResult, int usedMoney) {
        this.lottoMatchResults = gameResult.getLottoMatchResults();
        this.usedMoney = usedMoney;
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
        return getProfit() / (usedMoney);
    }

    private double getProfit(){
        return getLottoRanks().stream()
                .mapToInt(LottoRank::getPrizeMoney)
                .sum();
    }
}
