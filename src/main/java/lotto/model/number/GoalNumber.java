package lotto.model.number;

import lotto.common.LottoResult;

import static lotto.common.LottoConfiguration.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Goal extends Lotto {
    private final Integer bonusBall;

    public Goal(List<Integer> goal, Integer bonusBall) {
        super(goal);
        validateBonusBall(bonusBall);
        this.bonusBall = bonusBall;
    }

    public LottoResult compareLotto(Lotto lotto) {
        return LottoResult.findLottoResult(getMatchCount(lotto), lotto.getNumbers().contains(bonusBall));
    }

    private Integer getMatchCount(Lotto lotto) {
        Set<Integer> set = new HashSet<>(lotto.getNumbers());
        set.addAll(getNumbers());

        return LOTTO_COUNT * 2 - set.size();
    }

    private void validateBonusBall(Integer bonusBall) {
        if (getNumbers().contains(bonusBall)) {
            throw new RuntimeException("보너스 볼이 당첨 번호와 겹쳐서는 안됩니다.");
        }
    }
}
