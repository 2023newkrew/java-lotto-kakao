package domain.lotto;

import domain.lotto.ticket.LottoNumber;

import java.util.HashSet;
import java.util.List;

public class WinningNumbers {
    private final List<LottoNumber> lottoNumbers;
    private final LottoNumber bonusNumber;

    public List<LottoNumber> getLottoNumber() {
        return lottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    public WinningNumbers(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers == null)
            throw new NullPointerException();
        validateNumberDuplication(winningNumbers, bonusNumber);
        validateNumberRange(winningNumbers);
        this.lottoNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateNumberDuplication(List<LottoNumber> lottoNumbers, LottoNumber bonusNumber) {
        HashSet<LottoNumber> hs = new HashSet<>(lottoNumbers);
        if (hs.size() != LottoConstant.LOTTO_NUMBER_SIZE) throw new IllegalArgumentException();
        if (hs.contains(bonusNumber)) throw new IllegalArgumentException();
    }

    private void validateNumberRange(List<LottoNumber> lottoNumbers) {
        lottoNumbers
                .forEach((number) -> {
                    if (number.getNumber() < LottoConstant.MIN_LOTTO_NUMBER || number.getNumber() > LottoConstant.MAX_LOTTO_NUMBER)
                        throw new IllegalArgumentException();
                });
    }
}
