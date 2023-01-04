package lotto.domain;

import java.util.List;

public class LottoWinningNumberList {

    private final LottoNumberSet lottoNumbers;
    private final LottoNumber bonusNumber;

    public LottoWinningNumberList(List<Integer> numberList, int bonusNumber) {
        this(new LottoNumberSet(numberList), LottoNumber.from(bonusNumber));
    }

    public LottoWinningNumberList(LottoNumberSet lottoNumberSet, LottoNumber bonusLottoNumber) {
        validateDuplicateBonusNumber(lottoNumberSet, bonusLottoNumber);
        this.lottoNumbers = lottoNumberSet;
        this.bonusNumber = bonusLottoNumber;
    }

    private void validateDuplicateBonusNumber(LottoNumberSet lottoNumberSet,
            LottoNumber bonusLottoNumber) {
        if (lottoNumberSet.hasNumber(bonusLottoNumber)) {
            throw new IllegalArgumentException("보너스 번호는 다른 당첨번호와 중복될 수 없습니다.");
        }
    }

    public int matchBaseNumbers(LottoNumberSet lottoNumberSet) {
        return this.lottoNumbers.getAmountOfNumbersInNumbers(lottoNumberSet);
    }

    public boolean matchBonusNumber(LottoNumberSet lottoNumberSet) {
        return lottoNumberSet.hasNumber(bonusNumber);
    }
}
