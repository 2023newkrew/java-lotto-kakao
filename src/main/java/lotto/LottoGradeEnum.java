package lotto;

import java.util.Arrays;

public enum LottoGradeEnum {

    FIRST(6, 2_000_000_000, false),
    SECOND(5, 300_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5000, false),
    NONE_GRADE(0, 0, false);

    private static final int MIN_MATCH_COUNT = 0;
    private static final int MAX_MATCH_COUNT = 6;
    private final int matchCount;
    private final int price;
    private final boolean needBonusMatch;

    LottoGradeEnum(int matchCount, int price, boolean needBonusMatch) {
        this.matchCount = matchCount;
        this.price = price;
        this.needBonusMatch = needBonusMatch;
    }

    public static LottoGradeEnum evaluate(int matchCount, boolean isMatchBonus) {
        validateMatchCount(matchCount);
        return Arrays.stream(LottoGradeEnum.values())
                .filter(lottoGrade -> lottoGrade.matchCount == matchCount)
                .filter(lottoGrade -> !lottoGrade.needBonusMatch || isMatchBonus)
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

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrice() {
        return price;
    }
}
