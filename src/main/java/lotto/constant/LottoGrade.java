package lotto.constant;

import static lotto.constant.ExceptionMessages.INVALID_MATCH_COUNT_RANGE;

import java.util.Arrays;

public enum LottoGrade {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 300_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);


    public final int matchCount;
    public final boolean isBonusMatches;
    public final int price;

    LottoGrade(int matchCount, boolean isBonusMatches, int price) {
        this.matchCount = matchCount;
        this.isBonusMatches = isBonusMatches;
        this.price = price;
    }

    public static LottoGrade getGrade(int matchCount, boolean isBonusMatches) {
        validateMatchCount(matchCount);
        return Arrays.stream(LottoGrade.values())
                .filter((lottoGrade) -> lottoGrade.matchCount == matchCount && lottoGrade.isBonusMatches == isBonusMatches)
                .findFirst()
                .orElse(NONE);
    }

    private static void validateMatchCount(int matchCount) {
        if (matchCount < 0 || matchCount > 6) {
            throw new IllegalArgumentException(INVALID_MATCH_COUNT_RANGE);
        }
    }
}
