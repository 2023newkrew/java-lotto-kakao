package lotto.domain;

import java.util.Arrays;

public enum LottoResult {
    NO_MATCH(0, false, 0),
    FIFTH_PLACE(3, false, 5_000),
    FOURTH_PLACE(4, false, 50_000),
    THIRD_PLACE(5, false, 1_500_000),
    SECOND_PLACE(5, true, 30_000_000),
    FIRST_PLACE(6, false, 2_000_000_000);

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
            return SECOND_PLACE;
        }
        return THIRD_PLACE;
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
