package lotto.domain;

import java.util.List;

public class WinningLotto {

    private final LottoNumberList lottoNumberList;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> numbers, int bonusNumber) {
        this.lottoNumberList = new LottoNumberList(numbers);
        LottoNumber bonusLottoNumber = new LottoNumber(bonusNumber);
        validateDuplicateBonusNumber(bonusLottoNumber);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    private void validateDuplicateBonusNumber(LottoNumber bonusLottoNumber) {
        if (lottoNumberList.hasNumber(bonusLottoNumber)) {
            throw new IllegalArgumentException("보너스 번호는 다른 당첨번호와 중복될 수 없습니다.");
        }
    }

    public int countCorrectNumbers(LottoNumberList lottoNumberList) {
        return this.lottoNumberList.countSameNumbers(lottoNumberList);
    }

    public boolean checkBonusNumberInNumberList(LottoNumberList lottoNumberList) {
        return lottoNumberList.hasNumber(bonusNumber);
    }
}
