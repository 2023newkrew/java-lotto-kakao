package lotto;

import static lotto.constant.MessageConstant.INVALID_GRADE_NUMBER_RANGE;
import static lotto.constant.MessageConstant.INVALID_MATCH_COUNT_RANGE;

import java.util.Arrays;

public enum LottoGradeEnum {

    FIRST(6, 2_000_000_000),
    SECOND(5, 300_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5000),
    NONE_GRADE(0, 0);


    public final int matchCount;
    public final int price;

    LottoGradeEnum(int matchCount, int price) {
        this.matchCount = matchCount;
        this.price = price;
    }

    public static LottoGradeEnum evaluate(int matchCount, boolean isMatchBonus) {
        validateMatchCount(matchCount);
        if (matchCount == SECOND.matchCount && isMatchBonus) {
            return SECOND;
        }
        return Arrays.stream(LottoGradeEnum.values())
                .filter((lottoGrade) -> lottoGrade.matchCount == matchCount)
                .findFirst()
                .orElse(NONE_GRADE);
    }

    private static void validateMatchCount(int matchCount) {
        if (matchCount < 0 || matchCount > 6) {
            throw new IllegalArgumentException(INVALID_MATCH_COUNT_RANGE);
        }
    }

    public static LottoGradeEnum getGrade(int gradeNumber) {
        if (gradeNumber < 1 || gradeNumber > 6) {
            throw new IllegalArgumentException(INVALID_GRADE_NUMBER_RANGE);
        }
        return LottoGradeEnum.values()[gradeNumber - 1];
    }
}
