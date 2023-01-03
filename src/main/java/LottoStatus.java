import java.util.Arrays;

public enum LottoStatus {
    FIRST_PLACE(6, false, 2000000000L),
    SECOND_PLACE(5, true, 30000000L),
    THIRD_PLACE(5, false, 1500000L),
    FOURTH_PLACE(4, false, 50000L),
    FIFTH_PLACE(3, false, 5000L),
    LOSE(0, false, 0L);

    private final Integer correctWinningNumberCount;
    private final Boolean isCorrectBonusNumber;
    private final Long reward;

    LottoStatus(Integer correctWinningNumberCount, Boolean isCorrectBonusNumber, Long reward) {
        this.correctWinningNumberCount = correctWinningNumberCount;
        this.isCorrectBonusNumber = isCorrectBonusNumber;
        this.reward = reward;
    }

    public Long getReward() {
        return reward;
    }

    public static LottoStatus getStatus(Integer correctWinningNumberCount, Boolean isCorrectBonusNumber) {
       return Arrays.stream(LottoStatus.values())
               .filter(lottoStatus -> {
                   return lottoStatus.correctWinningNumberCount.equals(correctWinningNumberCount)
                            && lottoStatus.isCorrectBonusNumber.equals(isCorrectBonusNumber);
               })
               .findFirst().orElseGet(()->LOSE);
    }
}
