package lotto.model.enums;

import lotto.model.MatchedResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public enum LottoResult {

    SIX_NUMBERS_MATCHED(List.of(6), false, 2_000_000_000),

    FIVE_NUMBERS_MATCHED_AND_BONUS_MATCHED(List.of(5), true, 30_000_000),

    FIVE_NUMBERS_MATCHED_AND_BONUS_NOT_MATCHED(List.of(5), false, 1_500_000),

    FOUR_NUMBERS_MATCHED(List.of(4), null, 50_000),

    THREE_NUMBERS_MATCHED(List.of(3), null, 5_000),

    LOSE(List.of(0, 1, 2), null, 0),
    ;

    private final List<Integer> mainMatchCountCase;

    private final Boolean bonusMatched;

    private final Integer prizeMoney;

    LottoResult(List<Integer> mainMatchCountCase, Boolean bonusMatched, Integer prizeMoney) {
        this.mainMatchCountCase = mainMatchCountCase;
        this.bonusMatched = bonusMatched;
        this.prizeMoney = prizeMoney;
    }

    public static LottoResult match(MatchedResult matchedResult) {
        return Arrays.stream(LottoResult.values())
                .filter(lottoResult -> lottoResult.mainMatchCountCase.contains(matchedResult.getMatchedCount())
                        && Optional.ofNullable(lottoResult.bonusMatched).orElseGet(matchedResult::getBonusNumberMatched) == matchedResult.getBonusNumberMatched())
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                ;
    }

    public Integer getPrizeMoney() {
        return prizeMoney;
    }

    private String[] convertToString() {
        return mainMatchCountCase.stream()
                .map(String::valueOf)
                .collect(Collectors.toList())
                .toArray(String[]::new)
                ;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(" ");
        stringJoiner
                .add(String.format("%s개 일치", String.join(", ", convertToString())))
                .add(Boolean.TRUE.equals(bonusMatched) ? "보너스 볼 일치" : "")
                .add(String.format("(%d원)", prizeMoney))
        ;
        return stringJoiner.toString();
    }
}