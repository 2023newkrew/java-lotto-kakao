package lotto.model;

import lotto.exception.DuplicatedBallNumber;

public class WinNumber {
    private final LottoTrial winNumber;
    private final LottoBall bonusNumber;

    public WinNumber(LottoTrial winNumber, LottoBall bonusNumber) {
        this.winNumber = winNumber;
        this.bonusNumber = bonusNumber;

        if (winNumber.getBalls().contains(bonusNumber)) {
            throw new DuplicatedBallNumber();
        }
    }

    public LottoResult compareLotto(LottoTrial trial) {
        int matchCount = 0;
        boolean matchBonus = false;

        for (LottoBall bn : trial.getBalls()) {
            matchCount += winNumber.getBalls().contains(bn) ? 1 : 0;
            matchBonus |= bn.equals(bonusNumber);
        }

        if (matchCount != LottoConstants.BALLCOUNT_LIMIT - 1) {
            matchBonus = false;
        }

        return new LottoResult(matchCount, matchBonus);
    }
}
