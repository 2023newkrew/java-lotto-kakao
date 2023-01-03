package lotto;

import buyer.BuyerResult;

import java.util.List;

public class LotteryResult {
    private final LotteryNumbers winningNumbers;
    private final LotteryNumber bonusNumber;

    public LotteryResult(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) throw new IllegalArgumentException("중복된 숫자가 존재합니다");

        this.winningNumbers = new LotteryNumbers(winningNumbers);
        this.bonusNumber = new LotteryNumber(bonusNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LotteryResult)) return false;

        LotteryResult cp = (LotteryResult) obj;

        return this.winningNumbers.equals(cp.winningNumbers) && this.bonusNumber.equals(cp.bonusNumber);
    }

    public Rank getRank(Lottery lottery) {
        int count = 0;
        List<LotteryNumber> lotteryNumbers = lottery.getLotteryNumber();

        for (final LotteryNumber number : winningNumbers.getNumbers()) {
            count += lotteryNumbers.contains(number) ? 1 : 0;
        }

        return Rank.getRank(new LotteryMatch(count, isBonusMatch(lottery)));
    }

    private boolean isBonusMatch(Lottery lottery) {
        return lottery.getLotteryNumber().contains(bonusNumber);
    }

    public BuyerResult getResult(Lotteries lotteries) {
        BuyerResult buyerResult = new BuyerResult();
        for (Lottery lottery : lotteries.getLotteries()) {
            buyerResult.matches(getRank(lottery));
        }
        return buyerResult;
    }
}
