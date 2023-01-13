package domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WinningStatistics {
    private final List<LottoMatchResult> lottoMatchResults;
    private final int usedMoney;

    private final Map<LottoRank, LottoRank> rankMap = Map.of(
            LottoRank.FIRST, LottoRank.FIRST,
            LottoRank.SECOND, LottoRank.SECOND,
            LottoRank.THIRD, LottoRank.THIRD,
            LottoRank.FOURTH, LottoRank.FOURTH,
            LottoRank.FOURTH_BONUS, LottoRank.FOURTH,
            LottoRank.FIFTH, LottoRank.FIFTH,
            LottoRank.FIFTH_BONUS, LottoRank.FIFTH,
            LottoRank.DEFAULT, LottoRank.DEFAULT
    );

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
                .map(rankMap::get)
                .toList();
    }

    public double getRateOfReturn(){
        return (double) getProfit() / (usedMoney);
    }

    public long getProfit(){
        return getLottoRanks().stream()
                .mapToLong(LottoRank::getPrizeMoney)
                .sum();
    }
}
