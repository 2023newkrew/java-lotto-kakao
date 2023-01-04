package lotto.model;

import lotto.model.enums.RankingType;

import java.util.*;

public class Winner {
    public RankingResult rankingCount(List<RankingType> rankingTypes) {
        Map<RankingType, Integer> rankingResult = new EnumMap<>(RankingType.class);
        for( RankingType rank : RankingType.values()){
            rankingResult.put(rank, 0);
        }
        for (RankingType rankingType : rankingTypes){
            rankingResult.put(rankingType, rankingResult.get(rankingType) + 1);
        }
        return new RankingResult(rankingResult);
    }

    public double revenue(RankingResult rankingResult, int money) {
        double prizeSum = 0;
        for (RankingType rank : rankingResult.getRankingResult().keySet()){
            prizeSum += rank.getPrize() * rankingResult.getRankingResult().get(rank);
        }
        return Math.round((prizeSum / money)*100) / 100.0;
    }
}
