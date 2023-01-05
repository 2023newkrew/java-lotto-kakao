package lotto.domain.lotterynumber;

import static lotto.constant.ExceptionMessage.BONUS_NUMBER_DUPLICATED;

public class WinningNumbers {
    private final LotteryNumberCombination winningLotteryNumberCombination;
    private final LotteryNumber bonusLotteryNumber;

    public WinningNumbers(LotteryNumberCombination winningLotteryNumberCombination, int bonusNumber) {
        this(winningLotteryNumberCombination, LotteryNumber.of(bonusNumber));
    }

    public WinningNumbers(LotteryNumberCombination winningLotteryNumberCombination, LotteryNumber bonusLotteryNumber) {
        validate(winningLotteryNumberCombination, bonusLotteryNumber);
        this.winningLotteryNumberCombination = winningLotteryNumberCombination;
        this.bonusLotteryNumber = bonusLotteryNumber;
    }

    public LotteryNumberCombination getWinningLotteryNumberCombination() {
        return winningLotteryNumberCombination;
    }

    public LotteryNumber getBonusLotteryNumber() {
        return bonusLotteryNumber;
    }

    private void validate(LotteryNumberCombination winningLotteryNumberCombination, LotteryNumber bonusLotteryNumber) {
        if (winningLotteryNumberCombination.contains(bonusLotteryNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATED);
        }
    }
}
