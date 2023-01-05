package lotto.utils;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, true, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, true, 50000),
    FIFTH(3, true, 5000),
    NONE(0, true, 0);

    private int matchCount;
    private boolean hasBonus;
    private final int prize;

    LottoRank(int matchCount, boolean hasBonus, int prize) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
        this.prize = prize;
    }

    public int getPrize(){
        return this.prize;
    }

    public static LottoRank getRank(int matchCount, boolean hasBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .filter(rank -> rank.hasBonus == hasBonus)
                .findFirst()
                .orElse(NONE);
    }
}
