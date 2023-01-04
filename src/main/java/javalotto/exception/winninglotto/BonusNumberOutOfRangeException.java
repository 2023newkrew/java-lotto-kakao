package javalotto.exception.winninglotto;

import static javalotto.util.LottoConstants.LOTTO_NUMBER_MAX_VALUE;
import static javalotto.util.LottoConstants.LOTTO_NUMBER_MIN_VALUE;

public class BonusNumberOutOfRangeException extends IllegalArgumentException {
    public BonusNumberOutOfRangeException(int bonusNumber) {
        this(String.format("보너스 번호는 %d 이상 %d 이하여야 합니다. 입력하신 보너스 번호: %d",
                LOTTO_NUMBER_MIN_VALUE, LOTTO_NUMBER_MAX_VALUE, bonusNumber));
    }

    public BonusNumberOutOfRangeException(String message) {
        super(message);
    }
}
