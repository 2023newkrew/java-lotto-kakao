package lotto.domain;

public enum LottoRank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000);

    public final int COUNT;
    public final int PRIZE;

    LottoRank(int count, int prize) {
        this.COUNT = count;
        this.PRIZE = prize;
    }
}
