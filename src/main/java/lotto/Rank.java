package lotto;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),
    NONE(0, false, 0);

    private static final int MATCH_FIVE = 5;

    public final int matchCount;
    public final boolean isBonusMatch;
    public final int prize;

    Rank(int matchCount, boolean isBonusMatch, int prize) {
        this.matchCount = matchCount;
        this.isBonusMatch = isBonusMatch;
        this.prize = prize;
    }

    public static Rank getRank(int matchCount, boolean isBonusMatch) {
        return Arrays.stream(values())
                .filter(rank -> {
                    if (matchCount == MATCH_FIVE) {
                        return rank.matchCount == matchCount && rank.isBonusMatch == isBonusMatch;
                    }
                    return rank.matchCount == matchCount;
                })
                .findFirst()
                .orElse(NONE);
    }
}
