package lotto.model;

import java.util.Arrays;
import java.util.List;

public enum LottoRank {
    RANK1(List.of(6), List.of(false, true), 2_000_000_000),
    RANK2(List.of(5), List.of(true), 30_000_000),
    RANK3(List.of(5), List.of(false), 1_500_000),
    RANK4(List.of(4), List.of(false, true), 50_000),
    RANK5(List.of(3), List.of(false, true), 5_000),
    RANK6(List.of(0, 1, 2), List.of(false, true), 0);

    private final List<Integer> matchedCount;
    private final List<Boolean> bonusBall;
    private final Integer reward;

    LottoRank(List<Integer> matchedCount, List<Boolean> bonusBall, Integer reward) {
        this.matchedCount = matchedCount;
        this.bonusBall = bonusBall;
        this.reward = reward;
    }

    public static LottoRank fromCountAndBonus(Integer count, boolean bonus) {
        return Arrays.stream(values())
                .filter(value -> {
                    if (!value.matchedCount.contains(5)) {
                        return value.matchedCount.contains(count);
                    }
                    return value.bonusBall.contains(bonus) && value.matchedCount.contains(count);
                })
                .findAny()
                .orElse(RANK6);
    }

    public List<Integer> getMatchedCount() {
        return matchedCount;
    }

    public Integer getReward() {
        return reward;
    }

}
