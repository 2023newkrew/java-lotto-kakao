package lotto;

import java.util.Arrays;

public enum Rank {
    FIRST(4,6),
    SECOND(3,5),
    THIRD(2,5),
    FOURTH(1,4),
    FIFTH(0,3);

    public final int index;
    public final int matches;

    Rank(int index, int matches) {
        this.index = index;
        this.matches = matches;
    }

    public static Rank getRank(int match){
        return Arrays.stream(values())
                .filter(Rank -> Rank.matches == match)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
