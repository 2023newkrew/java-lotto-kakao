package lotto.model.campany;

import lotto.model.store.Money;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoStats {

    private final Map<LottoRanking, Long> rankingCounts;

    private final Money totalPrize;

    public static LottoStats from(List<LottoRanking> rankings) {
        if (Objects.isNull(rankings)) {
            throw new IllegalArgumentException("로또 등수가 없습니다.");
        }
        Map<LottoRanking, Long> rankingCounts = rankings.stream()
                .collect(Collectors.toMap(p -> p, p -> 1L, Long::sum));
        Money totalPrize = calculateTotalPrize(rankingCounts);

        return new LottoStats(rankingCounts,totalPrize);
    }

    private static Money calculateTotalPrize(Map<LottoRanking, Long> rankingCounts) {
        return rankingCounts.keySet().stream()
                .map(sumEachPrize(rankingCounts))
                .reduce(Money::add)
                .orElse(Money.ZERO);
    }

    private static Function<LottoRanking, Money> sumEachPrize(Map<LottoRanking, Long> rankingCounts) {
        return r -> {
            Money prize = r.getPrize();
            long rankingCount = rankingCounts.get(r);

            return prize.multiply(BigDecimal.valueOf(rankingCount));
        };
    }

    public LottoStats(Map<LottoRanking, Long> rankingCounts,Money totalPrize) {
        this.rankingCounts = rankingCounts;
        this.totalPrize = totalPrize;
    }

    public long countBy(LottoRanking ranking) {
        return rankingCounts.getOrDefault(ranking, 0L);
    }

    public Money getTotalPrize() {
        return totalPrize;
    }
}
