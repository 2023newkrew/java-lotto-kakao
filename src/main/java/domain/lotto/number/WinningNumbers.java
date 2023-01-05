package domain.lotto.number;

import exception.BonusNumberDuplicationException;

import java.util.HashSet;
import java.util.List;

public class WinningNumbers {
    private final LottoNumbers winningNumbers;
    private final LottoNumber bonusNumber;

    public List<Integer> getLottoNumber() {
        return winningNumbers.getNumbers();
    }

    public int getBonusNumber() {
        return bonusNumber.getNumber();
    }

    public WinningNumbers(final List<Integer> winningNumbers, final int bonusNumber) {
        validateBonusNumberDuplication(winningNumbers, bonusNumber);
        this.winningNumbers = new LottoNumbers(winningNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    private void validateBonusNumberDuplication(final List<Integer> lottoNumbers, final int bonusNumber) {
        HashSet<Integer> lottoNumberSet = new HashSet<>(lottoNumbers);
        if (lottoNumberSet.contains(bonusNumber))
            throw new BonusNumberDuplicationException();
    }

}
