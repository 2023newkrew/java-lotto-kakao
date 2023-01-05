package lotto.model;

import java.util.Objects;

public class MatchedResult {

    private final Integer matchedMainNumberCount;

    private final Boolean isBonusNumberMatched;

    public MatchedResult(Integer matchedMainNumberCount, Boolean isBonusNumberMatched) {
        this.matchedMainNumberCount = matchedMainNumberCount;
        this.isBonusNumberMatched = isBonusNumberMatched;
    }

    public Integer getMatchedMainNumberCount() {
        return matchedMainNumberCount;
    }

    public Boolean getBonusNumberMatched() {
        return isBonusNumberMatched;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchedResult)) return false;
        MatchedResult that = (MatchedResult) o;
        return Objects.equals(matchedMainNumberCount, that.matchedMainNumberCount) && Objects.equals(isBonusNumberMatched, that.isBonusNumberMatched);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchedMainNumberCount, isBonusNumberMatched);
    }
}
