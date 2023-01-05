package domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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
        return statisticsTargetRanks.stream()
                .collect(Collectors.toMap(k -> k, v -> Collections.frequency(lottoRanks, v)));
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
