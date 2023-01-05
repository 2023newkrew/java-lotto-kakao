package lotto.domain;

import java.util.List;
import lotto.exception.ExceptionMessage;
import lotto.utils.ErrorMessageFormatter;

public class LottoAnswer {

    private final LottoNumbers lottoNumbers;
    private final BonusBall bonusBall;

    public static LottoAnswer createLottoAnswer(List<Integer> numbers, int bonusBall) {
        return new LottoAnswer(numbers, bonusBall);
    }

    private LottoAnswer(List<Integer> numbers, int bonusBall) {
        lottoNumbers = LottoNumbers.createLottoNumbers(numbers);
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


    public int match(LottoNumbers other) {
        return lottoNumbers.match(other);
    }

    public boolean hasBonusBall(LottoNumbers other){
        return bonusBall.hasBonusBall(other);
    }

}
