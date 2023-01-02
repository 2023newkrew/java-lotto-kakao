package lotto.model;

import lotto.exception.ErrorCode;
import lotto.exception.LottoException;

public class LottoWinningNumber {
    private final LottoTicket winningNumber;
    private final Integer bonusBall;

    public LottoWinningNumber(LottoTicket lottoTicket, Integer bonusBall) {
        this.winningNumber = lottoTicket;
        validateBonusBall(bonusBall);
        this.bonusBall = bonusBall;
    }

    private void validateBonusBall(Integer bonusBall) {
        if(winningNumber.contains(bonusBall)) {
            throw new LottoException(ErrorCode.INVALID_BONUS_BALL);
        }
    }

}
