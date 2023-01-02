package domain;

import java.util.Arrays;

public enum LottoResult {
    NO_MATCH(0, false, new Money(0)),
    THREE_MATCH(3, false, new Money(5_000)),
    FOUR_MATCH(4, false, new Money(50_000)),
    FIVE_MATCH(5, false, new Money(1_500_000)),
    FIVE_MATCH_WITH_BONUS(5, true, new Money(30_000_000)),
    SIX_MATCH(6, false, new Money(2_000_000_000));

    private final int matchCount;
    private final boolean bonusBallMatch;
    private final Money money;

    LottoResult(int matchCount, boolean bonusBallMatch, Money money) {
        this.matchCount = matchCount;
        this.bonusBallMatch = bonusBallMatch;
        this.money = money;
    }

    public static LottoResult findResult(int matchCount, boolean bonusBallMatch) {
        if (matchCount == 5) {
            return decideFiveMatchResult(bonusBallMatch);
        }

        return Arrays.stream(values())
                .filter(lottoResult -> lottoResult.matchCount == matchCount)
                .findFirst()
                .orElse(NO_MATCH);
    }

    private static LottoResult decideFiveMatchResult(boolean bonusBallMatch) {
        if (bonusBallMatch) {
            return FIVE_MATCH_WITH_BONUS;
        }
        return FIVE_MATCH;
    }
}
