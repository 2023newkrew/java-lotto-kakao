package util.validator;

import common.constant.Constants;
import domain.LottoNumber;
import domain.WinningLotto;
import exception.DuplicateNumberException;

import java.util.List;

public class LottoNumberValidator {

    public static void validate(String input) {
        validateNumberFormat(input);
        int number = Integer.parseInt(input);
        validateInRange(number);
    }

    public static void validate(WinningLotto winningLotto, String bonusInput) {
        validate(bonusInput);
        LottoNumber bonusNumber = new LottoNumber(bonusInput);
        List<LottoNumber> winningLottoNumbers = winningLotto.getWinningLottoNumbers();
        if(winningLottoNumbers.contains(bonusNumber)){
            throw new DuplicateNumberException("보너스 숫자가 당첨 번호 중 하나와 중복됩니다.");
        }
    }

    protected static void validateNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자가 아닌 문자열이 입력되었습니다.");
        }
    }

    private static void validateInRange(int inputNumber) {
        if (!(Constants.MINIMUM <= inputNumber && inputNumber <= Constants.MAXIMUM)) {
            throw new IllegalArgumentException("1 이상 45 이하의 범위를 벗어났습니다.");
        }
    }
}
