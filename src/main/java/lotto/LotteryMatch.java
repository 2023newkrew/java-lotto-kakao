package lotto;

public class LotteryMatch {
    private final int matchCount;
    private final Boolean isBonusMatch;

    public LotteryMatch(int matchCount, Boolean isBonusMatch) {
        this.matchCount = matchCount;
        this.isBonusMatch = isBonusMatch;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public Boolean getIsBonusMatch() {
        return isBonusMatch;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LotteryMatch)) return false;

        LotteryMatch cp = (LotteryMatch) obj;

        return (this.matchCount == cp.matchCount && this.isBonusMatch == cp.isBonusMatch);
    }
}
