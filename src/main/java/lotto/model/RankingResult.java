package lotto.model;

import lotto.model.enums.RankingType;

import java.util.Collections;
import java.util.Map;

public class RankingResult {
    private final Map<RankingType, Integer> rankingResult;

    public RankingResult(Map<RankingType, Integer> rankingResult) {
        this.rankingResult = rankingResult;
    }

    public Map<RankingType, Integer> getRankingResult() {
        return Collections.unmodifiableMap(rankingResult);
    }
}
