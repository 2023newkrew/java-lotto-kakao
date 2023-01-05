package lotto.model.enums;

import lotto.model.MatchedResult;

import java.util.Arrays;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public enum LottoResult {

    SIX_NUMBERS_MATCHED(Set.of(6), false, 2_000_000_000),

    FIVE_NUMBERS_MATCHED_AND_BONUS_MATCHED(Set.of(5), true, 30_000_000),

    FIVE_NUMBERS_MATCHED_AND_BONUS_NOT_MATCHED(Set.of(5), false, 1_500_000),

    FOUR_NUMBERS_MATCHED(Set.of(4), false, 50_000),

    THREE_NUMBERS_MATCHED(Set.of(3), false, 5_000),

    LOSE(Set.of(0, 1, 2), false, 0),
    ;

    private final Set<Integer> matchedMainNumberRange;

    private final Boolean needToCheckBonus;

    private final Integer prizeMoney;

    LottoResult(Set<Integer> matchedMainNumberRange, Boolean needToCheckBonus, Integer prizeMoney) {
        this.matchedMainNumberRange = matchedMainNumberRange;
        this.needToCheckBonus = needToCheckBonus;
        this.prizeMoney = prizeMoney;
    }

    public static LottoResult valueOf(MatchedResult matchedResult) {
        return Arrays.stream(LottoResult.values())
                .filter(lottoResult -> match(lottoResult, matchedResult))
                .findFirst()
                .orElse(LottoResult.LOSE)
                ;
    }

    private static Boolean match(LottoResult lottoResult, MatchedResult matchedResult) {
        if (!lottoResult.needToCheckBonus) {
            return lottoResult.matchedMainNumberRange.contains(matchedResult.getMatchedMainNumberCount());
        }

        return lottoResult.matchedMainNumberRange.contains(matchedResult.getMatchedMainNumberCount())
                && lottoResult.needToCheckBonus.equals(matchedResult.getBonusNumberMatched());
    }

    public Integer getPrizeMoney() {
        return prizeMoney;
    }

    @Override
    public String toString() {
        if (this == LottoResult.LOSE) {
            return String.format("꽝 (%d원)", prizeMoney);
        }

        StringJoiner stringJoiner = new StringJoiner(" ");
        stringJoiner
                .add(String.format("%s개 일치",
                        matchedMainNumberRange.stream()
                                .map(String::valueOf)
                                .collect(Collectors.joining(", "))))
                .add(Boolean.TRUE.equals(needToCheckBonus) ? "보너스 볼 일치" : "")
                .add(String.format("(%d원)", prizeMoney))
        ;
        return stringJoiner.toString();
    }
}