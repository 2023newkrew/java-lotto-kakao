package domain.lotto;

import java.util.List;

public class LotteryResult {
    private final Lottery winningLottery;
    private final LotteryNumber bonusNumber;

    public LotteryResult(List<Integer> winningNumbers, int bonusNumber) {
        this(new LotteryNumbers(winningNumbers), bonusNumber);
    }

    public LotteryResult(LotteryNumbers winningNumber, int bonusNumber) {
        this.winningLottery = new Lottery(winningNumber);
        this.bonusNumber = new LotteryNumber(bonusNumber);

        if (winningNumber.contains(this.bonusNumber)) throw new IllegalArgumentException("중복된 숫자가 존재합니다");
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LotteryResult)) return false;

        LotteryResult cp = (LotteryResult) obj;

        return this.winningLottery.equals(cp.winningLottery) && this.bonusNumber.equals(cp.bonusNumber);
    }

    public Rank getRank(Lottery lottery) {
        return Rank.getRank(new LotteryMatch(lottery.getMatchCount(winningLottery), isBonusMatch(lottery)));
    }

    private boolean isBonusMatch(Lottery lottery) {
        return lottery.contains(bonusNumber);
    }
}
