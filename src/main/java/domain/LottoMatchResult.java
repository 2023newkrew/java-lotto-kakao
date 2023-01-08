package domain;

import java.util.Objects;

public class LottoMatchResult {
    private final int matchCount;
    private final boolean isBonusNumberMatched;

    public LottoMatchResult(int matchCount, boolean isBonusNumberMatched) {
        if(!isValidLottoMatchResult(matchCount, isBonusNumberMatched)){
            throw new IllegalArgumentException("해당 일치 수와 보너스 일치 여부는 적절하지 않습니다.");
        }
        this.matchCount = matchCount;
        this.isBonusNumberMatched = isBonusNumberMatched;
    }

    private boolean isValidLottoMatchResult(int matchCount, boolean isBonusNumberMatched) {
        if(matchCount == 6 && isBonusNumberMatched
            || matchCount < 0){
            return false;
        }
        return true;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusNumberMatched() {
        return isBonusNumberMatched;
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
