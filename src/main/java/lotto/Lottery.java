package lotto;

import java.util.List;

public class Lottery {
    private final LotteryNumbers lotteryNumbers;

    public Lottery(List<Integer> numbers) {
        this.lotteryNumbers = new LotteryNumbers(numbers);
    }

    public LotteryNumbers getLotteryNumber() {
        return lotteryNumbers;
    }

    public boolean contains(LotteryNumber lotteryNumber) {
        return lotteryNumbers.contains(lotteryNumber);
    }
}
