package domain;

import java.util.Arrays;

public enum LottoPlace {
    FIRST_PLACE(6, false, 2000000000L),
    SECOND_PLACE(5, true, 30000000L),
    THIRD_PLACE(5, false, 1500000L),
    FOURTH_PLACE(4, false, 50000L),
    FIFTH_PLACE(3, false, 5000L),
    LOSE(0, false, 0L);

    private final Integer correctWinningNumberCount;
    private final Boolean isCorrectBonusNumber;
    private final Long reward;

    LottoPlace(Integer correctWinningNumberCount, Boolean isCorrectBonusNumber, Long reward) {
        this.correctWinningNumberCount = correctWinningNumberCount;
        this.isCorrectBonusNumber = isCorrectBonusNumber;
        this.reward = reward;
    }

    public Long getReward() {
        return reward;
    }

    public static LottoPlace getStatus(Integer correctWinningNumberCount, Boolean isCorrectBonusNumber) {
       return Arrays.stream(LottoPlace.values())
               .filter(lottoStatus -> {
                   if (lottoStatus.correctWinningNumberCount == correctWinningNumberCount && lottoStatus.correctWinningNumberCount ==5) {
                       return lottoStatus.isCorrectBonusNumber == isCorrectBonusNumber;
                   }
                   return lottoStatus.correctWinningNumberCount == correctWinningNumberCount;
               })
               .findFirst().orElseGet(()->LOSE);
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
