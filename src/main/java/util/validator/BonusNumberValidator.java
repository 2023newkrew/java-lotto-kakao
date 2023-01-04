package util.validator;

import domain.WinningLotto;
import exception.DuplicateNumberException;

import java.util.List;

public class BonusNumberValidator {
    public static void validate(WinningLotto winningLotto, int bonusNumber) {
        List<Integer> winningLottoNumbers = winningLotto.getWinningLottoNumbers();
        if(winningLottoNumbers.contains(bonusNumber)){
            throw new DuplicateNumberException("보너스 숫자가 당첨 번호 중 하나와 중복됩니다.");
        }
    }
}
