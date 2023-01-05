package domain.lotto.number;

import domain.lotto.LottoMetaData;
import domain.lotto.number.LottoNumbers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WinningNumbers {
    private final LottoNumbers winningNumbers;
    private final LottoNumber bonusNumber;

    public List<Integer> getLottoNumber() {
        return winningNumbers.getNumbers();
    }

    public Integer getBonusNumber() {
        return bonusNumber.getNumber();
    }

    public WinningNumbers(final List<Integer> winningNumbers, final Integer bonusNumber) {
        if (winningNumbers == null || bonusNumber == null)
            throw new NullPointerException();
        validateBonusNumberDuplication(winningNumbers, bonusNumber);
        this.winningNumbers = new LottoNumbers(winningNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    private void validateBonusNumberDuplication(final List<Integer> lottoNumbers, final Integer bonusNumber) {
        HashSet<Integer> lottoNumberSet = new HashSet<>(lottoNumbers);
        if (lottoNumberSet.contains(bonusNumber))
            throw new IllegalArgumentException("보너스 번호가 당첨 번호에 이미 존재합니다.");
    }

}
