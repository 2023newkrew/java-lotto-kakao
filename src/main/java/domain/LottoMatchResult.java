package domain;

import java.util.Objects;

public class LottoMatchResult {
    private final int matchCount;
    private final boolean isBonusNumberMatched;

    public LottoMatchResult(int matchCount, boolean isBonusNumberMatched) {
        this.matchCount = matchCount;
        this.isBonusNumberMatched = isBonusNumberMatched;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoMatchResult that = (LottoMatchResult) o;
        return matchCount == that.matchCount && isBonusNumberMatched == that.isBonusNumberMatched;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchCount, isBonusNumberMatched);
    }
}
