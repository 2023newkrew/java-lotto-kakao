package lotto.domain;

import static lotto.constant.ExceptionMessages.INVALID_DUPLICATED_LOTTO_NUMBER;

import java.util.HashSet;
import java.util.List;

import lotto.constant.LottoGrade;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> numbers, int bonusNumber) {
        validateDuplicated(numbers, bonusNumber);
        this.lotto = Lotto.generateLotto(numbers);
        this.bonusNumber = LottoNumber.from(bonusNumber);
    }

    private void validateDuplicated(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(INVALID_DUPLICATED_LOTTO_NUMBER);
        }
    }

    public Lotto getLotto() {
        return lotto;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    public LottoGrade getGrade(Lotto lotto) {
        HashSet<LottoNumber> winningLottoNumberSet = new HashSet<>(this.lotto.getNumbers());
        int matchCount = (int) lotto.getNumbers().stream()
                .filter(winningLottoNumberSet::contains)
                .count();
        return LottoGrade.getGrade(matchCount, winningLottoNumberSet.contains(bonusNumber));
    }
}
