package lotto.domain;

public enum LottoRank {

    FIRST(5, 2_000_000_000, 6, false),
    SECOND(4, 30_000_000, 5, true),
    THIRD(3, 1_500_000, 5, false),
    FOURTH(2, 50_000, 4, false),
    FIFTH(1, 5_000, 3, false),
    SIXTH(0, 0, 2, false);

    public long getWinning() {
        return winning;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isRequiredBonus() {
        return requiredBonus;
    }

    public int getIndex() {
        return index;
    }

    private final long winning;
    private final int index;

    private final int matchCount;

    private final boolean requiredBonus;

    LottoRank(int index, long winning, int matchCount, boolean requiredBonus) {
        this.winning = winning;
        this.index = index;
        this.matchCount = matchCount;
        this.requiredBonus = requiredBonus;
    }
}
