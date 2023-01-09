package lotto.model.ranking;

import lotto.model.store.Money;

import java.util.Arrays;
import java.util.Objects;

public class TotalPrize {

    private final Money totalPrize;

    public static TotalPrize from(RankingCounts rankingCounts) {
        Money totalPrize = calculateTotalPrize(rankingCounts);

        return new TotalPrize(totalPrize);
    }

    private static Money calculateTotalPrize(RankingCounts rankingCounts) {
        if (Objects.isNull(rankingCounts)) {
            return Money.ZERO;
        }

        return Arrays.stream(LottoRanking.values())
                .map(rankingCounts::sumEachPrize)
                .reduce(Money::add)
                .orElse(Money.ZERO);
    }

    private TotalPrize(Money totalPrize) {
        this.totalPrize = totalPrize;
    }

    public Money getMoney() {
        return totalPrize;
    }
}
