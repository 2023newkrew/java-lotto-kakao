package lotto.model.enums;

import lotto.model.MatchedResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public enum LottoResult {

    // 6
    SIX_NUMBERS_MATCHED_AND_BONUS_NOT_MATCHED(List.of(6), false, 2_000_000_000),

    FIVE_NUMBERS_MATCHED_AND_BONUS_MATCHED(List.of(5), true, 30_000_000),

    // 5
    FIVE_NUMBERS_MATCHED_AND_BONUS_NOT_MATCHED(List.of(5), false, 1_500_000),

    FOUR_NUMBERS_MATCHED_AND_BONUS_MATCHED(List.of(4), true, 1_500_000),

    // 4
    FOUR_NUMBERS_MATCHED_AND_BONUS_NOT_MATCHED(List.of(4), false, 50_000),

    THREE_NUMBERS_MATCHED_AND_BONUS_MATCHED(List.of(3), true, 50_000),

    // 3
    THREE_NUMBERS_MATCHED_AND_BONUS_NOT_MATCHED(List.of(3), false, 5_000),

    TWO_NUMBERS_MATCHED_AND_BONUS_MATCHED(List.of(2), true, 5_000),

    // etc
    LOSE(List.of(), null, 0),
    ;

    private final List<Integer> matchedMainNumberCount;

    private final Boolean bonusMatched;

    private final Integer prizeMoney;

    LottoResult(List<Integer> matchedMainNumberCount, Boolean bonusMatched, Integer prizeMoney) {
        this.matchedMainNumberCount = matchedMainNumberCount;
        this.bonusMatched = bonusMatched;
        this.prizeMoney = prizeMoney;
    }

    public static LottoResult match(MatchedResult matchedResult) {
        return Arrays.stream(LottoResult.values())
                .filter(lottoResult -> lottoResult.matchedMainNumberCount.contains(matchedResult.getMatchedCount())
                        && Optional.ofNullable(lottoResult.bonusMatched).orElseGet(matchedResult::getBonusNumberMatched) == matchedResult.getBonusNumberMatched())
                .findFirst()
                .orElse(LottoResult.LOSE)
                ;
    }

    public Integer getPrizeMoney() {
        return prizeMoney;
    }

    private String[] convertToString() {
        return matchedMainNumberCount.stream()
                .map(String::valueOf)
                .collect(Collectors.toList())
                .toArray(String[]::new)
                ;
    }

    @Override
    public String toString() {
        if (this == LottoResult.LOSE) {
            return String.format("꽝 (%d원)", prizeMoney);
        }

        StringJoiner stringJoiner = new StringJoiner(" ");
        stringJoiner
                .add(String.format("%s개 일치", String.join(", ", convertToString())))
                .add(Boolean.TRUE.equals(bonusMatched) ? "보너스 볼 일치" : "")
                .add(String.format("(%d원)", prizeMoney))
        ;
        return stringJoiner.toString();
    }
}