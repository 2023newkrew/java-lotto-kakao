package lotto.models;

import static lotto.common.LotteryConfiguration.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.models.enums.Rank;

public class Goal extends Lottery {
    private final Integer bonusBall;

    public Goal(List<Integer> goal, Integer bonusBall) {
        super(goal);
        validateBonusBall(bonusBall);
        this.bonusBall = bonusBall;
    }

    public Rank compareLottery(Lottery lottery) {
        return Rank.findRank(getMatchCount(lottery), lottery.getNumbers().contains(bonusBall));
    }

    private Integer getMatchCount(Lottery lottery) {
        Set<Integer> sum = new HashSet<>(lottery.getNumbers());
        sum.addAll(getNumbers());
        return LOTTERY_COUNT * 2 - sum.size();
    }

    private void validateBonusBall(Integer bonusBall) {
        validateBonusBallIsNotDuplicated(bonusBall);
        validateBonusBallRange(bonusBall);
    }

    private void validateBonusBallRange(Integer bonusBall) {
        if (bonusBall < 1 || bonusBall > 45) {
            throw new RuntimeException("보너스 볼은 " + MIN_VALUE + "부터" + MAX_VALUE + "사이의 수 이어야 합니다.");
        }
    }

    private void validateBonusBallIsNotDuplicated(Integer bonusBall) {
        if (getNumbers().contains(bonusBall)) {
            throw new RuntimeException("보너스 볼이 당첨 번호와 겹쳐서는 안됩니다.");
        }
    }
}
