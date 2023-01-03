package domain.lotto;

import java.util.HashSet;
import java.util.List;

public class WinningNumbers {
    private final List<Integer> lottoNumbers;
    private final Integer bonusNumber;

    public List<Integer> getLottoNumber() {
        return lottoNumbers;
    }

    public Integer getBonusNumber() {
        return bonusNumber;
    }

    public WinningNumbers(List<Integer> winningNumbers, Integer bonusNumber) {
        if (winningNumbers == null || bonusNumber == null)
            throw new NullPointerException();
        validateNumberDuplication(winningNumbers, bonusNumber);
        validateNumberRange(winningNumbers);
        this.lottoNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateNumberDuplication(List<Integer> lottoNumbers, Integer bonusNumber) {
        HashSet<Integer> hs = new HashSet<>(lottoNumbers);
        if (hs.size() != LottoConstantData.LOTTO_NUMBER_SIZE) throw new IllegalArgumentException();
        if (hs.contains(bonusNumber)) throw new IllegalArgumentException();
    }

    private void validateNumberRange(List<Integer> lottoNumbers) {
        lottoNumbers
                .forEach((number) -> {
                    if (number < LottoConstantData.MIN_LOTTO_NUMBER || number > LottoConstantData.MAX_LOTTO_NUMBER)
                        throw new IllegalArgumentException();
                });
    }
}
