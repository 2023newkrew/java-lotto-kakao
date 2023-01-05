package lotto.domain;

import static lotto.domain.LottoConstants.LOTTO_SIZE;
import static lotto.domain.LottoConstants.MIN_LOTTO_SIZE;
import static lotto.exception.ExceptionMessage.*;
import static lotto.utils.ErrorMessageFormatter.makeErrorMessage;

import lotto.exception.ExceptionMessage;
import lotto.utils.ErrorMessageFormatter;

public class LottoResult {

    private final int matchCount;
    private final boolean hasBonus;

    public static LottoResult makeResult(int matchCount, boolean hasBonus) {
        validate(matchCount, hasBonus);
        return new LottoResult(matchCount, hasBonus);
    }

    private static void validate(int matchCount, boolean hasBonus) {
        if (matchCount < MIN_LOTTO_SIZE || getSum(matchCount, hasBonus) > LOTTO_SIZE) {
            throw new IllegalArgumentException(
                    makeErrorMessage(INVALID_MATCH_COUNT_AND_HAS_BONUS, String.format("%d %s", matchCount, hasBonus),
                            "matchCount, hasBonus"));
        }
    }

    private static double getSum(int matchCount, boolean hasBonus) {
        if (hasBonus) {
            return matchCount + 1;
        }
        return matchCount;
    }

    private LottoResult(int matchCount, boolean hasBonus) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
    }

    public LottoRank getRank() {
        return LottoRank.caculateLottoRank(matchCount, hasBonus);
    }

}
