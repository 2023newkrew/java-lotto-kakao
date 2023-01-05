package lotto;

import static lotto.constant.MessageConstant.INVALID_GRADE_NUMBER_RANGE;

import java.util.Arrays;

public enum LottoGradeEnum {

    FIRST(6, 2_000_000_000),
    SECOND(5, 300_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5000),
    NONE_GRADE(0, 0);

    private static final int MIN_MATCH_COUNT = 0;
    private static final int MAX_MATCH_COUNT = 6;
    private final int matchCount;
    private final int price;

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
                .filter((lottoGrade) -> lottoGrade != SECOND & lottoGrade.matchCount == matchCount)
                .findFirst()
                .orElse(NONE_GRADE);
    }

    private static void validateMatchCount(int matchCount) {
        if (matchCount < MIN_MATCH_COUNT || matchCount > MAX_MATCH_COUNT) {
            throw new IllegalArgumentException(
                    String.format("로또의 갯수가 %d개이기 때문에 match count는 %d이상 %d이하의 값을 가져야 합니다.",
                            MAX_MATCH_COUNT,
                            MIN_MATCH_COUNT,
                            MAX_MATCH_COUNT
                    )
            );
        }
    }

    public static LottoGradeEnum getGrade(int gradeNumber) {
        if (gradeNumber < 1 || gradeNumber > 6) {
            throw new IllegalArgumentException(INVALID_GRADE_NUMBER_RANGE);
        }
        return LottoGradeEnum.values()[gradeNumber - 1];
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrice() {
        return price;
    }
}
