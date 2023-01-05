package lotto.model;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class WinningNumbers {

    private final Lotto winningLottoNumbers;

    private final Integer bonusNumber;

    private WinningNumbers(Lotto winningLottoNumbers, Integer bonusNumber) {
        this.winningLottoNumbers = winningLottoNumbers;
        this.bonusNumber = bonusNumber;
        validateBonusNumber();
    }

    public WinningNumbers(List<Integer> winningLottoNumbers, Integer bonusNumber) {
        this(new Lotto(winningLottoNumbers), bonusNumber);
    }

    private void validateBonusNumber() {
        if (bonusNumber < LottoSettings.MIN_RANGE.getValue()
                || bonusNumber > LottoSettings.MAX_RANGE.getValue()) {
            throw new IllegalArgumentException();
        }

        if (winningLottoNumbers.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException();
        }
    }

    public MatchedResult match(Lotto lotto) {
        return new MatchedResult(matchWinningLottoNumbers(lotto), checkBonusNumber(lotto));
    }

    private Integer matchWinningLottoNumbers(Lotto lotto) {
        Set<Integer> standard = new TreeSet<>(winningLottoNumbers.getNumbers());
        standard.retainAll(new TreeSet<>(lotto.getNumbers()));
        return standard.size();
    }

    private Boolean checkBonusNumber(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }
}
