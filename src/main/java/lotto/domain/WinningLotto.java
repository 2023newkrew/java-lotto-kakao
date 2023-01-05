package lotto.domain;

public class WinningLotto {
    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        validateBonusNumber(lottoNumbers, bonusNumber);
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        if (lottoNumbers.containsLottoNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호와 로또 번호가 중복되었습니다.");
        }
    }

    public LottoNumbers lottoNumbers() {
        return lottoNumbers;
    }

    public LottoNumber bonusNumber() {
        return bonusNumber;
    }

}
