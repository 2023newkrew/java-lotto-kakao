package lotto.model.ranking;

import lotto.model.store.Money;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RankingCounts {

    private final Map<LottoRanking, Long> rankingCounts;

    public static RankingCounts from(List<LottoRanking> rankings) {
        if (Objects.isNull(rankings)) {
            throw new IllegalArgumentException("로또 등수가 없습니다.");
        }

        return new RankingCounts(convertToMap(rankings));
    }

    private static Map<LottoRanking, Long> convertToMap(List<LottoRanking> rankings) {
        return rankings.stream()
                .collect(Collectors.toMap(
                        p -> p,
                        p -> 1L,
                        Long::sum));
    }

    private RankingCounts(Map<LottoRanking, Long> rankingCounts) {
        this.rankingCounts = rankingCounts;
    }

    public long countBy(LottoRanking ranking) {
        return rankingCounts.getOrDefault(ranking, 0L);
    }

    public Money sumEachPrize(LottoRanking ranking) {
        return ranking.getPrize().multiply(countBy(ranking));
    }
}
