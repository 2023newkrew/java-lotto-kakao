package lotto.domain;

public enum LottoRank {

    FIRST(0, 2_000_000_000),
    SECOND(1, 30_000_000),
    THIRD(2, 1_500_000),
    FOURTH(3, 50_000),
    FIFTH(4, 5_000),
    SIXTH(5, 0);

    public long winning() {
        return winning;
    }
    public int index() {
        return index;
    }

    private final long winning;
    private final int index;

    LottoRank(int index, long winning) {
        this.index = index;
        this.winning = winning;
    }
}
