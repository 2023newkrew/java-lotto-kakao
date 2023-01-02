package lotto;

import java.util.List;

public class LottoWinningNumbers {

    private final LottoNumbers lottoNumbers;

    public LottoWinningNumbers(List<Integer> numbers, int bonusNumber) {
        this.lottoNumbers = new LottoNumbers(numbers);
        LottoNumber bonusLottoNumber = new LottoNumber(bonusNumber);
        validateDuplicateBonusNumber(bonusLottoNumber);
    }

    private void validateDuplicateBonusNumber(LottoNumber bonusLottoNumber) {
        if (lottoNumbers.hasNumber(bonusLottoNumber)) {
            throw new IllegalArgumentException("보너스 번호는 다른 당첨번호와 중복될 수 없습니다.");
        }
    }
}
