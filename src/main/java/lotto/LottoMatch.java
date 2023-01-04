package lotto;

public class LottoMatch {
    private static final int MATCH_FIVE = 5;
    private final int matchCount;
    private final Boolean isBonusMatch;

    public LottoMatch(int matchCount, Boolean isBonusMatch) {
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
        if (!(obj instanceof LottoMatch)) return false;

        LottoMatch cp = (LottoMatch) obj;

        return (this.matchCount == cp.matchCount && this.isBonusMatch == cp.isBonusMatch);
    }
}
