package lotto.model.company;

import lotto.model.store.Money;

import java.util.function.Predicate;
import java.util.stream.Stream;

public enum LottoRanking {
    NOTHING(0L, 0, false),
    FIFTH(5_000L, 3, false),
    FOURTH(50_000L, 4, false),
    THIRD(150_000L, 5, false),
    SECOND(30_000_000L, 5, true),
    FIRST(2_000_000_000L, 6, false);

    private final Money prize;

    private final int commonCount;

    private final boolean hasBonus;

    LottoRanking(long prize, int commonCount, boolean hasBonus) {
        this.prize = Money.valueOf(prize);
        this.commonCount = commonCount;
        this.hasBonus = hasBonus;
    }

    public static LottoRanking from(int commonCount, boolean hasBonus) {
        if(isSecond(commonCount,hasBonus)){
            return SECOND;
        }
        return Stream.of(LottoRanking.values())
                .filter(commonCountIs(commonCount))
                .findAny()
                .orElse(NOTHING);
    }

    private static boolean isSecond(int commonCount, boolean hasBonus) {
        return commonCount == SECOND.commonCount && hasBonus == SECOND.hasBonus;
    }

    private static Predicate<LottoRanking> commonCountIs(int commonCount) {
        return r -> r.commonCount == commonCount;
    }

    public Money getPrize() {
        return prize;
    }
}
