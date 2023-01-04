package domain;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static domain.LottoRank.*;

public class LottoMatchStatistics {
    private final List<LottoMatchResult> lottoMatchResults;
    private final List<LottoRank> statisticsTargetRanks = List.of(FIRST, SECOND, THIRD, FOURTH, FIFTH);
    public LottoMatchStatistics(List<LottoMatchResult> lottoMatchResults) {
        this.lottoMatchResults = lottoMatchResults;
    }

    public Map<LottoRank, Integer> getRankStatistics(){
        List<LottoRank> lottoRanks = getLottoRanks();
        Map<LottoRank, Integer> rankStatistics = new TreeMap<>();

        for(LottoRank lottoRank : statisticsTargetRanks){
            rankStatistics.put(lottoRank, 0);
        }

        for(LottoRank lottoRank : lottoRanks){
            int originCount = rankStatistics.get(lottoRank);
            rankStatistics.put(lottoRank, originCount + 1);
        }
        return rankStatistics;
    }

    private List<LottoRank> getLottoRanks(){
        return lottoMatchResults.stream()
                .map((LottoRank::from))
                .filter((statisticsTargetRanks::contains))
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
