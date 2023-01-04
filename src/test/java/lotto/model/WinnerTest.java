package lotto.model;

import lotto.model.enums.RankingType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class WinnerTest {
    @Test
    void checkRankingCount() {
        Winner winner = new Winner();
        RankingType Rank1 = RankingType.FIRST;
        RankingType Rank2 = RankingType.THIRD;
        RankingType Rank3 = RankingType.DEFAULT;
        List<RankingType> input = Arrays.asList(Rank1, Rank2, Rank3);

        Map<RankingType, Integer> rankingResult  = new EnumMap<>(RankingType.class) {{
            put(RankingType.FIRST, 1);
            put(RankingType.SECOND, 0);
            put(RankingType.THIRD, 1);
            put(RankingType.FOURTH, 0);
            put(RankingType.FIFTH, 0);
            put(RankingType.DEFAULT, 1);
        }};

        Assertions.assertEquals(winner.rankingCount(input).getRankingResult(), rankingResult);
    }

    @Test
    void calRevenue(){
        Winner winner = new Winner();

        int money = 3000;
        Map<RankingType, Integer> rankingResult  = new EnumMap<>(RankingType.class) {{
            put(RankingType.FIRST, 1);
            put(RankingType.SECOND, 0);
            put(RankingType.THIRD, 1);
            put(RankingType.FOURTH, 0);
            put(RankingType.FIFTH, 0);
            put(RankingType.DEFAULT, 1);
        }};


        Assertions.assertEquals(winner.revenue(new RankingResult(rankingResult), money), 667166.67);
    }
}