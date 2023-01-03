package lotto.model;

import java.util.*;

/**
 * 당첨된 로또의 수를 등별로 계산하고, 수익률을 계산합니다.
 * 컨트롤러의 비즈니스 로직을 최소
 * 해시 맵에 각 랭크 enum을 키로 하고, value는 해당 rank를 가진 로또의 개수입니다.
 * 수익률은 (전체 소득) / (로또 구매 비용) 입니다.
 */
public class Winner {
    public Map<Ranking, Integer> rankingCount(List<Ranking> rankings) {
        Map<Ranking, Integer> rankingResult = new EnumMap<>(Ranking.class);
        for( Ranking rank : Ranking.values()){
            rankingResult.put(rank, 0);
        }
        for (Ranking ranking : rankings){
            rankingResult.put(ranking, rankingResult.get(ranking) + 1);
        }
        return Collections.unmodifiableMap(rankingResult);
    }

    public double revenue(Map<Ranking, Integer> rankingResult, int money) {
        double prizeSum = 0;
        for (Ranking rank : rankingResult.keySet()){
            prizeSum += rank.getPrize() * rankingResult.get(rank);
        }
        return Math.round((prizeSum / money)*100) / 100.0;
    }
}
