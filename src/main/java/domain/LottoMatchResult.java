package domain;

import java.util.Objects;

public class LottoMatchResult {
    private static final String INVALID_MATCH_COUNT_AND_IS_BONUS_NUMBER_MATCHED_MSG = "해당 일치 수와 보너스 일치 여부는 적절하지 않습니다.";
    private static final int MATCH_COUNT_MIN_VALUE = 0;
    private final int matchCount;
    private final boolean isBonusNumberMatched;

    public LottoMatchResult(int matchCount, boolean isBonusNumberMatched) {
        if(!isValidLottoMatchResult(matchCount, isBonusNumberMatched)){
            throw new IllegalArgumentException(INVALID_MATCH_COUNT_AND_IS_BONUS_NUMBER_MATCHED_MSG);
        }
        this.matchCount = matchCount;
        this.isBonusNumberMatched = isBonusNumberMatched;
    }

    private boolean isValidLottoMatchResult(int matchCount, boolean isBonusNumberMatched) {
        if(matchCount == LottoConstant.LOTTO_NUMBERS_LENGTH && isBonusNumberMatched
            || matchCount < MATCH_COUNT_MIN_VALUE){
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
