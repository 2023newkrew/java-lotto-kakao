package dto;

import domain.Lotto;
import domain.LottoNumber;

public class WinningLotto {
    private static final String ERROR_BONUS_DUPLICATE = "보너스번호와 당첨번호는 중복될 수 없습니다.";
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        validateWinningLotto(winningNumbers, bonusNumber);
        this.lotto = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateWinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.has(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_BONUS_DUPLICATE);
        }
    }

    public Lotto getLotto() {
        return lotto;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
