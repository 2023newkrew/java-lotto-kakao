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
               .filter(lottoStatus -> {
                   if (lottoStatus.matchCount.equals(matchCount) && lottoStatus.matchCount == 5) {
                       return lottoStatus.isBonus.equals(isBonus);
                   }
                   return lottoStatus.matchCount.equals(matchCount);
               })
               .findFirst().orElseGet(() -> NO_RANK);
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append(matchCount).append("개 일치");
        if (isBonus) {
            str.append(", 보너스 볼 일치");
        }
        str.append(" (").append(reward).append("원)");
        return str.toString();
    }
}
