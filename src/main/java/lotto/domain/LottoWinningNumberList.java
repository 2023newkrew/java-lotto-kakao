package lotto.domain;

import java.util.List;

public class LottoWinningNumberList {

    private final LottoNumberSet lottoNumbers;
    private final LottoNumber bonusNumber;

    public LottoWinningNumberList(List<Integer> numberList, int bonusNumber) {
        this.lottoNumbers = new LottoNumberSet(numberList);
        LottoNumber bonusLottoNumber = LottoNumber.from(bonusNumber);
        validateDuplicateBonusNumber(bonusLottoNumber);
        this.bonusNumber = LottoNumber.from(bonusNumber);
    }

    private void validateDuplicateBonusNumber(LottoNumber bonusLottoNumber) {
        if (lottoNumbers.hasNumber(bonusLottoNumber)) {
            throw new IllegalArgumentException("보너스 번호는 다른 당첨번호와 중복될 수 없습니다.");
        }
    }

    public int getAmountOfNumbersInWinningNumbers(LottoNumberSet lottoNumbers) {
        return this.lottoNumbers.getAmountOfNumbersInNumbers(lottoNumbers);
    }

    public boolean checkBonusNumberInNumbers(LottoNumberSet lottoNumbers) {
        return lottoNumbers.hasNumber(bonusNumber);
    }
}
