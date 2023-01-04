package lotto.models;

import static lotto.common.LotteryConfiguration.MAX_VALUE;
import static lotto.common.LotteryConfiguration.MIN_VALUE;

import java.util.List;
import lotto.models.enums.Rank;

public class WinningLottery {

    private final Lottery lottery;

    private final LotteryNumber bonusBall;

    public WinningLottery(List<Integer> numbers, Integer bonusBall) {
        this.lottery = new Lottery(numbers);
        validateBonusBall(bonusBall);
        this.bonusBall = LotteryNumber.from(bonusBall);
    }

    public Rank compareLottery(Lottery lottery) {
        return Rank.findRank(this.lottery.compare(lottery), lottery.contains(bonusBall));
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
        if (lottery.contains(LotteryNumber.from(bonusBall))) {
            throw new RuntimeException("보너스 볼이 당첨 번호와 겹쳐서는 안됩니다.");
        }
    }
}
