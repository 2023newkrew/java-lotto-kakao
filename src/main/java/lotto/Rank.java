package lotto;

import java.util.Arrays;

public enum Rank {
    FIRST(new LottoMatch(6, false), 2000000000),
    SECOND(new LottoMatch(5, true), 30000000),
    THIRD(new LottoMatch(5, false), 1500000),
    FOURTH(new LottoMatch(4, false), 50000),
    FIFTH(new LottoMatch(3, false), 5000),
    NONE(new LottoMatch(0, false), 0);

    public final LottoMatch match;
    public final int prize;

    Rank(LottoMatch match, int prize) {
        this.match = match;
        this.prize = prize;
    }

    public static Rank getRank(LottoMatch match) {
        return Arrays.stream(values())
                .filter(rank -> rank.match.equals(match))
                .findFirst()
                .orElse(NONE);
    }
}
