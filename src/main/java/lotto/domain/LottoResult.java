package lotto.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum LottoResult {
    NO_MATCH(((matchCount, bonusBallMatch) -> matchCount < 3), 0),
    FIFTH_PLACE(((matchCount, bonusBallMatch) -> matchCount == 3), 5_000),
    FOURTH_PLACE(((matchCount, bonusBallMatch) -> matchCount == 4), 50_000),
    THIRD_PLACE(((matchCount, bonusBallMatch) -> matchCount == 5 && !bonusBallMatch), 1_500_000),
    SECOND_PLACE(((matchCount, bonusBallMatch) -> matchCount == 5 && bonusBallMatch), 30_000_000),
    FIRST_PLACE(((matchCount, bonusBallMatch) -> matchCount == 6), 2_000_000_000);

    private final BiPredicate<Integer, Boolean> matchCondition;
    private final int prizeMoney;

    LottoResult(BiPredicate<Integer, Boolean> matchCondition, int prizeMoney) {
        this.matchCondition = matchCondition;
        this.prizeMoney = prizeMoney;
    }

    public static LottoResult findResult(int matchCount, boolean bonusBallMatch) {
        return Arrays.stream(values())
                .filter(lottoResult -> lottoResult.matchCondition.test(matchCount, bonusBallMatch))
                .findFirst()
                .orElse(NO_MATCH);
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
