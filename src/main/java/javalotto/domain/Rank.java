package javalotto.domain;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import static javalotto.domain.Rank.BonusMatchType.*;

public enum Rank {
    FIRST(6, ALL, 2_000_000_000),
    SECOND(5, MATCH, 30_000_000),
    THIRD(5, MISMATCH, 1_500_000),
    FOURTH(4, ALL, 50_000),
    FIFTH(3, ALL, 5_000);

    private final int matchCount;
    private final BonusMatchType bonusMatchType;
    private final int prize;

    Rank(int matchCount, BonusMatchType bonusMatchType, int prize) {
        this.matchCount = matchCount;
        this.bonusMatchType = bonusMatchType;
        this.prize = prize;
    }

    public static Optional<Rank> from(int matchCount, boolean isBonusMatch) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isRank(matchCount, isBonusMatch))
                .findFirst();
    }

    private boolean isRank(int matchCount, boolean isBonusMatch) {
        return this.matchCount == matchCount && this.bonusMatchType.isSatisfiedBy(isBonusMatch);
    }

    public int getPrize() {
        return prize;
    }

    @Override
    public String toString() {
        return matchCount + "개 일치" + bonusMatchType + "(" + prize + "원)";
    }

     enum BonusMatchType {
        ALL(isBonusMatch -> true),
        MATCH((isBonusMatch) -> isBonusMatch),
        MISMATCH((isBonusMatch) -> !isBonusMatch);

        private final Predicate<Boolean> bonusMatchTypeCondition;

        BonusMatchType(Predicate<Boolean> bonusMatchTypeCondition) {
            this.bonusMatchTypeCondition = bonusMatchTypeCondition;
        }

        private boolean isSatisfiedBy(boolean isBonusNumberMatch) {
            return this.bonusMatchTypeCondition.test(isBonusNumberMatch);
        }

        @Override
        public String toString() {
            return this == MATCH ? ", 보너스 볼 일치" : " ";
        }
    }
}