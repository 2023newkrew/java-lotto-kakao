package lotto.models.enums;

import java.util.Arrays;

public enum Rank {
    FIRST(2_000_000_000, 6, false),
    SECOND(30_000_000, 5, true),
    THIRD(1_500_000, 5, false),
    FOURTH(50_000, 4, false),
    FIFTH(5_000, 3, false),
    NONE(0, 0, false);

    private final long prize;

    private final int matchCount;

    private final boolean includeBonus;

    Rank(long prize, int matchCount, boolean includeBonus) {
        this.prize = prize;
        this.matchCount = matchCount;
        this.includeBonus = includeBonus;
    }

    public long getPrize() {
        return prize;
    }

    static public Rank findRank(Integer matchCount, boolean includeBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.match(matchCount, includeBonus))
                .findFirst()
                .orElse(Rank.NONE);
    }

    private boolean match(Integer matchCount, boolean includeBonus) {
        if (matchCount != this.matchCount) {
            return false;
        }
        if (matchCount != 5) {
            return true;
        }
        return includeBonus == this.includeBonus;
    }
}
