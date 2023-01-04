package lotto.model;

import lotto.exception.InvalidLottoResult;

import java.util.Objects;

public class LottoResult {
    private final int matchCount;
    private final boolean matchBonus;

    public LottoResult(int matchCount, boolean matchBonus) {
        if (matchCount < 0 || matchCount > LottoConstants.BALLCOUNT_LIMIT ||
                (matchCount==LottoConstants.BALLCOUNT_LIMIT && matchBonus)){
            throw new InvalidLottoResult();
        }

        this.matchBonus = matchBonus;
        this.matchCount = matchCount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isMatchBonus() {
        return matchBonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        LottoResult that = (LottoResult) o;
        return matchCount == that.getMatchCount() && matchBonus == that.isMatchBonus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchCount, matchBonus);
    }

    @Override
    public String toString() {
        String result = matchCount + "개 일치";

        if (matchBonus){
            result += ", 보너스 볼 일치";
        }

        return result;
    }
}
