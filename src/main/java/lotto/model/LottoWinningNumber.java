package lotto.model;

import lotto.exception.ErrorCode;
import lotto.exception.LottoException;

public class LottoWinningNumber {
    private final LottoTicket winningNumber;
    private final LottoNumber bonusBall;

    public LottoWinningNumber(LottoTicket lottoTicket, LottoNumber bonusBall) {
        this.winningNumber = lottoTicket;
        validateBonusBall(bonusBall);
        this.bonusBall = bonusBall;
    }

    private void validateBonusBall(LottoNumber bonusBall) {
        if(winningNumber.contains(bonusBall)) {
            throw new LottoException(ErrorCode.INVALID_BONUS_BALL);
        }
    }

    public LottoRank checkLottoRank(LottoTicket lottoTicket){
        Integer sameCount = winningNumber.countOverlappingNumber(lottoTicket);
        boolean isBonus = lottoTicket.contains(bonusBall);

        return LottoRank.fromCountAndBonus(sameCount, isBonus);
    }
}
