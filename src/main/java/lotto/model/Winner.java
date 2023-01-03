package lotto.model;

import lotto.model.enums.RankingType;

import java.util.*;

public class Winner {
    public Map<RankingType, Integer> rankingCount(List<RankingType> rankingTypes) {
        Map<RankingType, Integer> rankingResult = new EnumMap<>(RankingType.class);
        for( RankingType rank : RankingType.values()){
            rankingResult.put(rank, 0);
        }
        for (RankingType rankingType : rankingTypes){
            rankingResult.put(rankingType, rankingResult.get(rankingType) + 1);
        }
        return Collections.unmodifiableMap(rankingResult);
    }

    public double revenue(Map<RankingType, Integer> rankingResult, int money) {
        double prizeSum = 0;
        for (RankingType rank : rankingResult.keySet()){
            prizeSum += rank.getPrize() * rankingResult.get(rank);
        }
        return Math.round((prizeSum / money)*100) / 100.0;
    }
}
