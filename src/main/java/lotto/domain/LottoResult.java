package lotto.domain;

import java.util.Arrays;

public enum LottoResult {
    NO_MATCH(0, false, 0),
    THREE_MATCH(3, false, 5_000),
    FOUR_MATCH(4, false, 50_000),
    FIVE_MATCH(5, false, 1_500_000),
    FIVE_MATCH_WITH_BONUS(5, true, 30_000_000),
    SIX_MATCH(6, false, 2_000_000_000);

    private final int matchCount;
    private final boolean bonusBallMatch;
    private final int prizeMoney;

    LottoResult(int matchCount, boolean bonusBallMatch, int prizeMoney) {
        this.matchCount = matchCount;
        this.bonusBallMatch = bonusBallMatch;
        this.prizeMoney = prizeMoney;
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

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusBallMatch() {
        return bonusBallMatch;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
