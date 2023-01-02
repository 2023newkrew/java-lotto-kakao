package lotto;

import buyer.BuyerResult;

import java.util.List;

public class LotteryResult {
    private final LotteryNumber winningNumbers;
    private final int bonusNumber;

    public LotteryResult(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = new LotteryNumber(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof LotteryResult)) return false;

        LotteryResult cp = (LotteryResult) obj;

        return this.winningNumbers.equals(cp.winningNumbers) && this.bonusNumber == cp.bonusNumber;
    }

    public Rank getRank(Lottery lottery) {
        int count = 0;
        List<Integer> lotteryNumbers = lottery.getLotteryNumber();

        for(final Integer number : winningNumbers.getNumbers()){
            count += lotteryNumbers.contains(number) ? 1 : 0;
        }

        return Rank.getRank(new LotteryMatch(count, isBonusMatch(lottery)));
    }

    private boolean isBonusMatch(Lottery lottery) {
        return lottery.getLotteryNumber().contains(bonusNumber);
    }

    public BuyerResult getResult(List<Lottery> lotteries) {
        BuyerResult buyerResult = new BuyerResult();
        for (Lottery lottery : lotteries) {
            buyerResult.matches(getRank(lottery));
        }
        return buyerResult;
    }
}
