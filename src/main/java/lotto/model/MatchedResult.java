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
        if (!(o instanceof MatchedResult)) return false;
        MatchedResult that = (MatchedResult) o;
        return Objects.equals(matchedCount, that.matchedCount) && Objects.equals(isBonusNumberMatched, that.isBonusNumberMatched);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchedCount, isBonusNumberMatched);
    }
}
