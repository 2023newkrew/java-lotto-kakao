package lotto.domain.lottoresult;

import static lotto.domain.LottoConstants.LOTTO_MIN_COUNT;
import static lotto.domain.LottoConstants.LOTTO_NUMBER_COUNT;
import static lotto.exception.ExceptionMessage.INVALID_LOTTO_RESULT_INPUT_EXCEPTION;

import java.util.Objects;
import lotto.exception.ErrorMessageFormatter;

public class DefaultLottoResult implements LottoResult {
    private final int matchCount;
    private final boolean hasBonus;

    public DefaultLottoResult(int matchCount, boolean hasBonus) {
        validate(matchCount, hasBonus);
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
    }

    private void validate(int matchCount, boolean hasBonus) {
        if (matchCount < LOTTO_MIN_COUNT || getSum(matchCount, hasBonus) > LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(
                    ErrorMessageFormatter.makeErrorMessage(INVALID_LOTTO_RESULT_INPUT_EXCEPTION,
                            String.format("matchCount: %d, hasBonus: %s ", matchCount, hasBonus),
                            "matchCount, hasBonus")
            );
        }

    }

    private int getSum(int matchCount, boolean hasBonus) {
        if (hasBonus) {
            return matchCount + 1;
        }
        return matchCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DefaultLottoResult that = (DefaultLottoResult) o;
        if (matchCount == 5) {
            return matchCount == that.matchCount && hasBonus == that.hasBonus;
        }
        return matchCount == that.matchCount;
    }

    @Override
    public int hashCode() {
        if (matchCount == 5) {
            return Objects.hash(matchCount, hasBonus);
        }
        return Objects.hash(matchCount);
    }
}
