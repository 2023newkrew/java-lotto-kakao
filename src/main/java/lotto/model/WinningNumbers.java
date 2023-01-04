package lotto.model;

import lotto.model.errors.LottoDuplicatedNumberException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {

    private final Lotto mainNumbers;

    private final LottoNumber bonusNumber;

    public WinningNumbers(Lotto mainNumbers, LottoNumber bonusNumber) {
        this.mainNumbers = mainNumbers;
        this.bonusNumber = bonusNumber;
        validateBonusNumber();
    }

    public WinningNumbers(List<Integer> mainNumbers, Integer bonusNumber) {
        this(Lotto.of(mainNumbers), new LottoNumber(bonusNumber));
    }

    private void validateBonusNumber() {
        if (mainNumbers.getNumbers().contains(bonusNumber)) {
            throw new LottoDuplicatedNumberException("보너스 볼과 로또 번호는 중복될 수 없습니다.");
        }
    }

    public MatchedResult check(Lotto lotto) {
        return new MatchedResult(checkMainNumbers(lotto), checkBonusNumber(lotto));
    }

    private Integer checkMainNumbers(Lotto lotto) {
        Set<LottoNumber> standard = new HashSet<>(mainNumbers.getNumbers());
        standard.retainAll(lotto.getNumbers());
        return standard.size();
    }

    private Boolean checkBonusNumber(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }
}
