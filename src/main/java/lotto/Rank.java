package lotto;

import java.util.Arrays;

public enum Rank {
    FIRST(4, new LotteryMatch(6,false), 2000000000),
    SECOND(3, new LotteryMatch(5,true),30000000),
    THIRD(2, new LotteryMatch(5,false),1500000),
    FOURTH(1, new LotteryMatch(4,false), 50000),
    FIFTH(0, new LotteryMatch(3,false), 5000);

    public final int index;
    public final LotteryMatch match;
    public final int prize;

    Rank(int index, LotteryMatch match, int prize) {
        this.index = index;
        this.match = match;
        this.prize = prize;
    }

    public static Rank getRank(LotteryMatch match){
        return Arrays.stream(values())
                .filter(Rank -> Rank.match.equals(match))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
