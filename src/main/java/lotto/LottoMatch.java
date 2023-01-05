package lotto;

import java.util.Objects;

public class LottoMatch {
    private final int matchCount;
    private final Boolean isBonusMatch;

    public LottoMatch(int matchCount, Boolean isBonusMatch) {
        this.matchCount = matchCount;
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

    @Override
    public int hashCode() {
        return Objects.hash(matchCount, isBonusMatch);
    }
}
