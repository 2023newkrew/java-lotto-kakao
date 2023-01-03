package lotto.domain;

public enum LottoRank {

    FIRST(2_000_000_000),
    SECOND(30_000_000),
    THIRD(1_500_000),
    FOURTH(50_000),
    FIFTH(5_000),
    SIXTH(0);

    public long winning() {
        return winning;
    }

    long winning;

    LottoRank(long winning) {
        this.winning = winning;
    }
}
