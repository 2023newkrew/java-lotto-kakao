package lotto.model;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

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

    public static LottoResult match(MatchedResult matchedResult) {
        return Arrays.stream(LottoResult.values())
                .filter(lottoResult -> lottoResult.mainMatchCountCase.contains(matchedResult.getMatchedCount()))
                .filter(lottoResult -> lottoResult.bonusMatched.contains(matchedResult.getBonusNumberMatched()))
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
                .add(bonusMatched.equals(List.of(true)) ? "보너스 볼 일치" : "")
                .add(String.format("(%d원)", prizeMoney))
        ;
        return stringJoiner.toString();
    }
}