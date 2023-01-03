package lotto.domain;

import static lotto.domain.LottoConstants.LOTTO_NUMBER_LOWER_BOUND;
import static lotto.domain.LottoConstants.LOTTO_NUMBER_UPPER_BOUND;
import static lotto.exception.ExceptionMessages.DUPLICATE_BONUS_NUMBER_EXCEPTION_MESSAGE;
import static lotto.exception.ExceptionMessages.OUT_OF_BOUNDS_EXCEPTION_MESSAGE;

public class LottoAnswer {
    private final LottoNumbers lottoNumbers;
    private final int bonusNumber;

    public LottoAnswer(LottoNumbers lottoNumbers, int bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        validateBonusNumber(lottoNumbers, bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(LottoNumbers lottoNumbers, int bonusNumber) {
        validateContains(lottoNumbers, bonusNumber);
        validateRange(bonusNumber);
    }

    private void validateContains(LottoNumbers lottoNumbers, int bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    private void validateRange(int bonusNumber) {
        if (bonusNumber < LOTTO_NUMBER_LOWER_BOUND.getValue() || bonusNumber > LOTTO_NUMBER_UPPER_BOUND.getValue()) {
            throw new IllegalArgumentException(OUT_OF_BOUNDS_EXCEPTION_MESSAGE);
        }
    }


    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }

    public int getBonus() {
        return bonusNumber;
    }
}
