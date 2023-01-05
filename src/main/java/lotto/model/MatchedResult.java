package lotto.model;

import java.util.Objects;

public class MatchedResult {

    private final Integer matchedCount;

    private final Boolean isBonusNumberMatched;

    public MatchedResult(Integer matchedCount, Boolean isBonusNumberMatched) {
        this.matchedCount = matchedCount;
        this.isBonusNumberMatched = isBonusNumberMatched;
    }

    public Integer getMatchedCount() {
        return matchedCount;
    }

    public Boolean getBonusNumberMatched() {
        return isBonusNumberMatched;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof MatchedResult) return false;

        MatchedResult that = (MatchedResult) o;

        if (!Objects.equals(matchedCount, that.matchedCount)) return false;
        return Objects.equals(isBonusNumberMatched, that.isBonusNumberMatched);
    }

    @Override
    public int hashCode() {
        int result = matchedCount != null ? matchedCount.hashCode() : 0;
        result = 31 * result + (isBonusNumberMatched != null ? isBonusNumberMatched.hashCode() : 0);
        return result;
    }
}
