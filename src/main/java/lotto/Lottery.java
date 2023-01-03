package lotto;

import java.util.List;

public class Lottery {
    private final LotteryNumbers lotteryNumbers;

    public Lottery(List<Integer> numbers) {
        this.lotteryNumbers = new LotteryNumbers(numbers);
    }

    public List<LotteryNumber> getLotteryNumber() {
        return lotteryNumbers.getNumbers();
    }
}
