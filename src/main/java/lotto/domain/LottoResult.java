package lotto.domain;

import static lotto.domain.LottoConstants.LOTTO_MIN_COUNT;
import static lotto.domain.LottoConstants.LOTTO_NUMBER_COUNT;
import static lotto.exception.ExceptionMessages.INVALID_LOTTO_RESULT_INPUT_EXCEPTION;

import java.util.Objects;

public class LottoResult {
    private final int matchCount;
    private final boolean hasBonus;

    public LottoResult(int matchCount, boolean hasBonus) {
        validate(matchCount, hasBonus);
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
    }

    private void validate(int matchCount, boolean hasBonus) {
        if ( matchCount < LOTTO_MIN_COUNT || getSum(matchCount, hasBonus) > LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(INVALID_LOTTO_RESULT_INPUT_EXCEPTION);
        }
    }

    private int getSum(int matchCount, boolean hasBonus) {
        if (hasBonus) {
            return matchCount + 1;
        }
        return matchCount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isHasBonus() {
        return hasBonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoResult that = (LottoResult) o;
        return matchCount == that.matchCount && hasBonus == that.hasBonus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchCount, hasBonus);
    }
}
