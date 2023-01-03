package domain;

import java.util.Arrays;

public enum Rank {
    FIRST_RANK(6, false, 2000000000L),
    SECOND_RANK(5, true, 30000000L),
    THIRD_RANK(5, false, 1500000L),
    FOURTH_RANK(4, false, 50000L),
    FIFTH_RANK(3, false, 5000L),
    LOSE(0, false, 0L);

    private final Integer correctWinningNumberCount;
    private final Boolean isCorrectBonusNumber;
    private final Long reward;

    Rank(Integer correctWinningNumberCount, Boolean isCorrectBonusNumber, Long reward) {
        this.correctWinningNumberCount = correctWinningNumberCount;
        this.isCorrectBonusNumber = isCorrectBonusNumber;
        this.reward = reward;
    }

    public Long getReward() {
        return reward;
    }

    public static Rank getLottoRank(Integer correctWinningNumberCount, Boolean isCorrectBonusNumber) {
       return Arrays.stream(Rank.values())
               .filter(lottoStatus -> {
                    return lottoStatus.isCorrectBonusNumber == isCorrectBonusNumber && lottoStatus.correctWinningNumberCount == correctWinningNumberCount;
               })
               .findFirst().orElseGet(() -> LOSE);
    }

    @Override
    public String toString() {
        StringBuffer placeString = new StringBuffer();
        placeString.append(correctWinningNumberCount + "개 일치");
        if (isCorrectBonusNumber) {
            placeString.append(", 보너스 볼 일치");
        }
        placeString.append(" (" + reward + "원)");
        return placeString.toString();
    }
}
