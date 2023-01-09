package lotto.model;

import lotto.exception.InvalidLottoResult;

import java.util.Objects;

public class LottoResult {
    private final int matchCount;
    private final boolean matchBonus;

    public LottoResult(int matchCount, boolean matchBonus) {
        isValidResult(matchCount, matchBonus);

        this.matchBonus = matchBonus;
        this.matchCount = matchCount;
    }

    private void isValidResult(int matchCount, boolean matchBonus) {
        if (matchCount < 0) {
            throw new InvalidLottoResult("matchCount should be positive integer");
        }
        if (matchCount > LottoConstants.BALLCOUNT_LIMIT) {
            throw new InvalidLottoResult("matchCount should be equal or smaller than BALLCOUNT_LIMIT");
        }
        if (matchCount == LottoConstants.BALLCOUNT_LIMIT && matchBonus) {
            throw new InvalidLottoResult("if matchCount is equal to BALLCOUNT_LIMIT, matchBonus should be false");
        }
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LottoResult that = (LottoResult)o;
        return matchCount == that.getMatchCount() && matchBonus == that.isMatchBonus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchCount, matchBonus);
    }
}
