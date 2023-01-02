package lotto;

import java.util.Arrays;

public enum Rank {
    FIRST(4, new LotteryMatch(6,false)),
    SECOND(3, new LotteryMatch(5,true)),
    THIRD(2, new LotteryMatch(5,false)),
    FOURTH(1, new LotteryMatch(4,false)),
    FIFTH(0, new LotteryMatch(3,false));

    public final int index;
    public final LotteryMatch match;

    Rank(int index, LotteryMatch match) {
        this.index = index;
        this.match = match;
    }

    public static Rank getRank(LotteryMatch match){
        return Arrays.stream(values())
                .filter(Rank -> Rank.match.equals(match))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
