package lotto.models;

import static lotto.common.LottoConfiguration.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Goal extends Lotto{
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
        Set<Integer> sum = new HashSet<>(lotto.getNumbers());
        sum.addAll(getNumbers());
        return LOTTO_COUNT * 2 - sum.size();
    }

//    private Prize compareLottoo(Lotto lotto) {
//        Integer matchCount = Math.toIntExact(lotto.getNumbers().stream()
//                .filter(i -> getNumbers().contains(i))
//                .count());
//        boolean matchBonus = lotto.getNumbers().contains(bonusBall);
//
//        return new Prize(matchCount, matchBonus);
//    }

    private void validateBonusBall(Integer bonusBall) {
        if (getNumbers().contains(bonusBall)) {
            throw new RuntimeException("보너스 볼이 당첨 번호와 겹쳐서는 안됩니다.");
        }
    }
}
