package dto;

import domain.Lotto;
import domain.LottoNumber;

import static utils.ErrorMessage.DUPLICATED_LOTTO_NUMBER_AND_BONUS_NUMBER;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        validateWinningLotto(winningNumbers, bonusNumber);
        this.lotto = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public Lotto getLotto() {
        return lotto;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    private void validateWinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.isIn(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATED_LOTTO_NUMBER_AND_BONUS_NUMBER.getMessage());
        }
    }
}
