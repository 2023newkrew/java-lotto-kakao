package domain.lotto;

public class LotteryMatch {
    private static final int MATCH_FIVE = 5;

    private final int matchCount;
    private final Boolean bonusMatch;

    public LotteryMatch(int matchCount, Boolean bonusMatch) {
        this.matchCount = matchCount;
        if (this.matchCount != MATCH_FIVE) {
            this.bonusMatch = false;
            return;
        }
        this.bonusMatch = bonusMatch;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isMatchWith(LotteryMatch cp) {
        if (this.matchCount == MATCH_FIVE)
            return this.matchCount == cp.matchCount && this.bonusMatch == cp.bonusMatch;

        return this.matchCount == cp.matchCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LotteryMatch)) return false;

        LotteryMatch cp = (LotteryMatch) obj;

        return (this.matchCount == cp.matchCount && this.bonusMatch == cp.bonusMatch);
    }
}
