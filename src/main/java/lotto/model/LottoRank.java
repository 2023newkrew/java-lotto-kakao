package lotto.model;

import java.util.Arrays;

public enum LottoRank {
    RANK1(6, false, 2000000000),
    RANK2(5, true, 30000000),
    RANK3(5, false, 1500000),
    RANK4(4, false, 50000),
    RANK5(3, false, 5000),
    RANK6(2, false, 0);

    private final Integer matchedCount;
    private final boolean bonusBall;
    private final Integer reward;

    LottoRank(Integer matchedCount, boolean bonusBall, Integer reward) {
        this.matchedCount = matchedCount;
        this.bonusBall = bonusBall;
        this.reward = reward;
    }

    public static LottoRank fromCountAndBonus(Integer count, boolean bonus) {
        return Arrays.stream(values())
                .filter(value -> {
                    if (value.matchedCount != 5) {
                        return value.matchedCount.equals(count);
                    }
                    return value.bonusBall == bonus && value.matchedCount.equals(count);
                })
                .findAny()
                .orElse(RANK6);
    }

    public Integer getMatchedCount() {
        return matchedCount;
    }

    public Integer getReward() {
        return reward;
    }

}
