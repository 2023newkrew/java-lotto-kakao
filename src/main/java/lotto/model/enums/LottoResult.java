package lotto.model.enums;

import lotto.model.MatchedResult;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

public enum LottoResult {

    SIX_NUMBERS_MATCHED(6, false, 2_000_000_000),

    FIVE_NUMBERS_MATCHED_AND_BONUS_MATCHED(5, true, 30_000_000),

    FIVE_NUMBERS_MATCHED_AND_BONUS_NOT_MATCHED(5, false, 1_500_000),

    FOUR_NUMBERS_MATCHED(4, null, 50_000),

    THREE_NUMBERS_MATCHED(3, null, 5_000),

    LOSE(null, null, 0),
    ;

    private final Integer matchedMainNumberCount;

    private final Boolean containsBonusNumber;

    private final Integer prizeMoney;

    LottoResult(Integer matchedMainNumberCount, Boolean containsBonusNumber, Integer prizeMoney) {
        this.matchedMainNumberCount = matchedMainNumberCount;
        this.containsBonusNumber = containsBonusNumber;
        this.prizeMoney = prizeMoney;
    }

    public static LottoResult match(MatchedResult matchedResult) {
        return Arrays.stream(LottoResult.values())
                .filter(lottoResult -> Objects.equals(lottoResult.matchedMainNumberCount, matchedResult.getMatchedCount())
                        && Optional.ofNullable(lottoResult.containsBonusNumber).orElseGet(matchedResult::getBonusNumberMatched) == matchedResult.getBonusNumberMatched())
                .findFirst()
                .orElse(LottoResult.LOSE)
                ;
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
                .add(String.format("%d개 일치", matchedMainNumberCount))
                .add(Boolean.TRUE.equals(containsBonusNumber) ? "보너스 볼 일치" : "")
                .add(String.format("(%d원)", prizeMoney))
        ;
        return stringJoiner.toString();
    }
}