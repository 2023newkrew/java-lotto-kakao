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

    public WinningNumbers(final List<Integer> winningNumbers, final Integer bonusNumber) {
        if (winningNumbers == null || bonusNumber == null)
            throw new NullPointerException();
        validateNumberDuplication(winningNumbers, bonusNumber);
        validateNumberRange(winningNumbers);
        this.lottoNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateNumberDuplication(final List<Integer> lottoNumbers, final Integer bonusNumber) {
        HashSet<Integer> hs = new HashSet<>(lottoNumbers);
        if (hs.size() != LottoMetaData.LOTTO_NUMBER_SIZE) throw new IllegalArgumentException();
        if (hs.contains(bonusNumber)) throw new IllegalArgumentException();
    }

    private void validateNumberRange(final List<Integer> lottoNumbers) {
        lottoNumbers
                .forEach((number) -> {
                    if (number < LottoMetaData.MIN_LOTTO_NUMBER || number > LottoMetaData.MAX_LOTTO_NUMBER)
                        throw new IllegalArgumentException();
                });
    }
}
