package lotto;

import util.StringParser;

import java.util.List;

public class LotteryResult {
    //    private final LotteryNumbers winningNumbers;
    private final Lottery winningLottery;
    private final LotteryNumber bonusNumber;

    public LotteryResult(String winningNumbersInput, int bonusNumber) {
        this(StringParser.parse(winningNumbersInput), bonusNumber);
    }

    public LotteryResult(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) throw new IllegalArgumentException("중복된 숫자가 존재합니다");

//        this.winningNumbers = new LotteryNumbers(winningNumbers);
        this.winningLottery = new Lottery(winningNumbers);
        this.bonusNumber = new LotteryNumber(bonusNumber);
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
