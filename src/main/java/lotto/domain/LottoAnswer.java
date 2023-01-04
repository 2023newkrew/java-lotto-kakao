package lotto.domain;

import java.util.List;
import lotto.exception.ExceptionMessage;
import lotto.utils.ErrorMessageFormatter;

public class LottoAnswer {

    LottoNumbers lottoNumbers;
    private BonusBall bonusBall;

    public static LottoAnswer makeLottoAnswer(List<Integer> numbers, int bonusBall) {
        return new LottoAnswer(numbers, bonusBall);
    }

    private LottoAnswer(List<Integer> numbers, int bonusBall) {
        lottoNumbers = LottoNumbers.makeLottoNumbers(numbers);
        validateBonusBall(lottoNumbers, bonusBall);
        this.bonusBall = BonusBall.createBonusBall(bonusBall);
    }

    private void validateBonusBall(LottoNumbers lottoNumbers, int bonusBall) {
        if (lottoNumbers.contains(bonusBall)) {
            throw new IllegalArgumentException(
                    ErrorMessageFormatter.makeErrorMessage(ExceptionMessage.BONUS_BALL_DUPLICATE_EXCEPTION_MESSAGE,
                            bonusBall, "duplicate bonus ball"));
        }
    }


}
