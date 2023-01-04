package lotto.models.enums;

import java.util.Arrays;

public enum Rank {
    FIRST(2_000_000_000, 6),
    SECOND(30_000_000, 5),
    THIRD(1_500_000, 5),
    FOURTH(50_000, 4),
    FIFTH(5_000, 3),
    NONE(0, 0);

    private final long prize;

    private final int matchCount;

    Rank(long prize, int matchCount) {
        this.prize = prize;
        this.matchCount = matchCount;
    }

    public long getPrize() {
        return prize;
    }

    static public Rank findRank(Integer matchCount, boolean includeBonus) {
        if (matchCount == 5 && !includeBonus) {
            return Rank.THIRD;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(Rank.NONE);
    }
}
