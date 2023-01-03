package domain.lotto;

import domain.lotto.number.LottoNumbers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WinningNumbers {
    private final LottoNumbers winningNumbers;
    private final Integer bonusNumber;

    public List<Integer> getLottoNumber() {
        return winningNumbers.getNumbers();
    }

    public Integer getBonusNumber() {
        return bonusNumber;
    }

    public WinningNumbers(final List<Integer> winningNumbers, final Integer bonusNumber) {
        if (winningNumbers == null || bonusNumber == null)
            throw new NullPointerException();
        validateBonusNumberDuplication(winningNumbers, bonusNumber);
        validateBonusNumberRange(bonusNumber);
        this.winningNumbers = new LottoNumbers(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumberDuplication(final List<Integer> lottoNumbers, final Integer bonusNumber) {
        HashSet<Integer> lottoNumberSet = new HashSet<>(lottoNumbers);
        if (lottoNumberSet.contains(bonusNumber))
            throw new IllegalArgumentException("보너스 번호가 당첨 번호에 이미 존재합니다.");
    }

    private void validateBonusNumberRange(final Integer number) {
        if (number < LottoMetaData.MIN_LOTTO_NUMBER || number > LottoMetaData.MAX_LOTTO_NUMBER)
            throw new IllegalArgumentException("보너스 번호는 1 이상 45 이하의 수를 입력하세요.");
    }
}
