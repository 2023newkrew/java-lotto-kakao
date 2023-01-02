package lotto.domain;

import static lotto.domain.LottoConstants.LOTTO_MIN_COUNT;
import static lotto.domain.LottoConstants.LOTTO_NUMBER_COUNT;

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
            throw new IllegalArgumentException();
        }
    }

    private int getSum(int matchCount, boolean hasBonus) {
        if (hasBonus) {
            return matchCount + 1;
        }
        return matchCount;
    }

}
