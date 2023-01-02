package javalotto.domain;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import static javalotto.domain.Rank.BonusMatchType.*;

public enum Rank {
    FIRST(6, ALL),
    SECOND(5, MATCH),
    THIRD(5, MISMATCH),
    FOURTH(4, ALL),
    FIFTH(3, ALL);

    final int matchCount;
    final BonusMatchType bonusMatchType;

    Rank(int matchCount, BonusMatchType bonusMatchType) {
        this.matchCount = matchCount;
        this.bonusMatchType = bonusMatchType;
    }

    public static Optional<Rank> from(int matchCount, boolean isBonusMatch) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isRank(matchCount, isBonusMatch))
                .findFirst();
    }

    public boolean isRank(int matchCount, boolean isBonusMatch) {
        return this.matchCount == matchCount && this.bonusMatchType.isSatisfiedBy(isBonusMatch);
    }

    enum BonusMatchType {
        ALL(isBonusMatch -> true),
        MATCH((isBonusMatch) -> isBonusMatch),
        MISMATCH((isBonusMatch) -> !isBonusMatch);

        final Predicate<Boolean> bonusMatchTypeCondition;

        BonusMatchType(Predicate<Boolean> bonusMatchTypeCondition) {
            this.bonusMatchTypeCondition = bonusMatchTypeCondition;
        }

        boolean isSatisfiedBy(boolean isBonusNumberMatch) {
            return this.bonusMatchTypeCondition.test(isBonusNumberMatch);
        }
    }
}