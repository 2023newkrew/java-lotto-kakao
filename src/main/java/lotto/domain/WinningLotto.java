package lotto.domain;

import static lotto.constant.MessageConstant.INVALID_DUPLICATED_LOTTO_NUMBER;

import java.util.List;
import java.util.Set;
import lotto.LottoGradeEnum;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> numbers, int bonusNumber) {
        validateDuplicated(numbers, bonusNumber);
        this.lotto = new Lotto(numbers);
        this.bonusNumber = LottoNumber.from(bonusNumber);
    }

    private void validateDuplicated(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(INVALID_DUPLICATED_LOTTO_NUMBER);
        }
    }

    public LottoGradeEnum getGrade(Lotto lotto) {
        Set<LottoNumber> winningLottoNumberSet = this.lotto.getNumbers();
        int matchCount = (int) lotto.getNumbers()
                .stream()
                .filter(winningLottoNumberSet::contains)
                .count();
        return LottoGradeEnum.evaluate(matchCount, lotto.getNumbers().contains(bonusNumber));
    }
}
