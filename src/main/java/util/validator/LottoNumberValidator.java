package util.validator;

import domain.Lotto;
import domain.AutoLottoGenerator;
import domain.LottoNumber;
import exception.DuplicateNumberException;

public class LottoNumberValidator {

    public static void validate(String input) {
        NumberValidator.validateNumberFormat(input);
        int number = Integer.parseInt(input);
        validateInRange(number);
    }

    public static void validate(Lotto winningLotto, String bonusInput) {
        validate(bonusInput);
        LottoNumber bonusNumber = new LottoNumber(bonusInput);
        if (winningLotto.containsNumber(bonusNumber)) {
            throw new DuplicateNumberException("보너스 숫자가 당첨 번호 중 하나와 중복됩니다.");
        }
    }

    private static void validateInRange(int inputNumber) {
        if (!(AutoLottoGenerator.MINIMUM <= inputNumber && inputNumber <= AutoLottoGenerator.MAXIMUM)) {
            throw new IllegalArgumentException("1 이상 45 이하의 범위를 벗어났습니다.");
        }
    }
}
