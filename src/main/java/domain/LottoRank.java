package domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST_RANK(6, false, 2000000000L),
    SECOND_RANK(5, true, 30000000L),
    THIRD_RANK(5, false, 1500000L),
    FOURTH_RANK(4, false, 50000L),
    FIFTH_RANK(3, false, 5000L),
    NO_RANK(0, false, 0L);

    private final Integer matchCount;
    private final Boolean isBonus;
    private final Long reward;

    LottoRank(Integer matchCount, Boolean isBonus, Long reward) {
        this.matchCount = matchCount;
        this.isBonus = isBonus;
        this.reward = reward;
    }

    public Long getReward() {
        return reward;
    }

    public static LottoRank getRank(Integer matchCount, Boolean isBonus) {
       return Arrays.stream(LottoRank.values())
               .filter(lottoRank -> isMatchLottoRank(lottoRank, matchCount, isBonus))
               .findFirst().orElseGet(() -> NO_RANK);
    }

    private static Boolean isMatchLottoRank(LottoRank lottoRank, Integer matchCount, Boolean isBonus) {
        if (lottoRank.matchCount.equals(matchCount) && LottoRank.SECOND_RANK.matchCount.equals(lottoRank.matchCount)) {
            return lottoRank.isBonus.equals(isBonus);
        }
        return lottoRank.matchCount.equals(matchCount);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(matchCount).append("개 일치");
        if (isBonus) {
            builder.append(", 보너스 볼 일치");
        }
        builder.append(" (").append(reward).append("원)");
        return builder.toString();
    }
}
