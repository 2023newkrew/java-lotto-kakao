package lotto;

public class LotteryMatch {
    private static final int MATCH_FIVE = 5;
    
    private final int matchCount;
    private final Boolean isBonusMatch;

    public LotteryMatch(int matchCount, Boolean isBonusMatch) {
        this.matchCount = matchCount;
        if (this.matchCount != MATCH_FIVE) {
            this.isBonusMatch = false;
            return;
        }
        this.isBonusMatch = isBonusMatch;
    }

    public int getMatchCount() {
        return matchCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LotteryMatch)) return false;

        LotteryMatch cp = (LotteryMatch) obj;

        return (this.matchCount == cp.matchCount && this.isBonusMatch == cp.isBonusMatch);
    }
}
