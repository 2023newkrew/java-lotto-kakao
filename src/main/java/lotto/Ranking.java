package lotto;

public enum Ranking {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    OTHER(0, 0);

    private final int matchingCount;
    private final int prize;

    Ranking(int matchingCount, int prize) {
        this.matchingCount = matchingCount;
        this.prize = prize;
    }

    public static Ranking matchRanking(int matchingCount, boolean matchBonusBall) {
        Ranking matchRanking = null;

        for (Ranking ranking : Ranking.values()) {
            matchRanking = ranking.match(matchingCount) ? ranking : matchRanking;
        }

        if (matchingCount == 5 && matchBonusBall) {
            matchRanking = SECOND;
        }

        return matchRanking == null ? OTHER : matchRanking;
    }

    public int getPrize() {
        return prize;
    }

    private boolean match(int matchingCount) {
        return matchingCount == this.matchingCount;
    }
}
