package lotto.model;

import java.util.Arrays;
import java.util.List;

public enum LottoResult {
    SIX_NUMBERS_MATCHED(List.of(6), List.of(true, false), 2000000000),
    FIVE_NUMBERS_MATCHED_AND_BONUS_MATCHED(List.of(5), List.of(true), 30000000),
    FIVE_NUMBERS_MATCHED_AND_BONUS_NOT_MATCHED(List.of(5), List.of(false), 1500000),
    FOUR_NUMBERS_MATCHED(List.of(4), List.of(true, false), 50000),
    THREE_NUMBERS_MATCHED(List.of(3), List.of(true, false), 5000),
    LOSE(List.of(0, 1, 2), List.of(true, false), 0);

    private final List<Integer> mainMatchCountCase;
    private final List<Boolean> bonusMatched;
    private final Integer prizeMoney;

    LottoResult(List<Integer> mainMatchCountCase, List<Boolean> bonusMatched, Integer prizeMoney) {
        this.mainMatchCountCase = mainMatchCountCase;
        this.bonusMatched = bonusMatched;
        this.prizeMoney = prizeMoney;
    }

    public Integer getPrizeMoney() {
        return prizeMoney;
    }

    public static LottoResult match(MatchedResult matchedResult) {
        return Arrays.stream(LottoResult.values())
                .filter(lottoResult -> lottoResult.mainMatchCountCase.contains(matchedResult.getMatchedCount()))
                .filter(lottoResult -> lottoResult.bonusMatched.contains(matchedResult.getBonusNumberMatched()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                ;
    }
}