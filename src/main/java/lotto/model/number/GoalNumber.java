package lotto.model.number;

import lotto.model.enums.LottoResultType;
import lotto.common.exception.InvalidInputException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class GoalNumber extends LottoNumber {
    private final Integer bonusBall;

    public GoalNumber(List<Integer> goal, Integer bonusBall) {
        super(goal);
        validateBonusBall(bonusBall);
        this.bonusBall = bonusBall;
    }

    public LottoResultType getLottoResult(LottoNumber lottoNumber) {
        return LottoResultType.findLottoResult(getMatchCount(lottoNumber), lottoNumber.getNumbers().contains(bonusBall));
    }

    public Integer getMatchCount(LottoNumber lottoNumber) {
        Set<Integer> set = new HashSet<>(lottoNumber.getNumbers());
        set.addAll(getNumbers());

        return LOTTO_COUNT * 2 - set.size();
    }

    private void validateBonusBall(Integer bonusBall) {
        if (getNumbers().contains(bonusBall)) {
            throw new InvalidInputException("보너스 볼이 당첨 번호와 겹쳐서는 안됩니다.");
        }
    }
}
