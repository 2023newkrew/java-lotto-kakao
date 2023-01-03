package lotto;

import java.util.Arrays;

public enum Rank {
    FIRST(new LotteryMatch(6, false), 2000000000),
    SECOND(new LotteryMatch(5, true), 30000000),
    THIRD(new LotteryMatch(5, false), 1500000),
    FOURTH(new LotteryMatch(4, false), 50000),
    FIFTH(new LotteryMatch(3, false), 5000),
    NONE(new LotteryMatch(0, false), 0);

    public final LotteryMatch match;
    public final int prize;

    Rank(LotteryMatch match, int prize) {
        this.match = match;
        this.prize = prize;
    }

    public static Rank getRank(LotteryMatch targetMatch) {
        return Arrays.stream(values())
                .filter(rank -> rank.match.isMatchWith(targetMatch))
                .findFirst()
                .orElse(NONE);
    }
}
