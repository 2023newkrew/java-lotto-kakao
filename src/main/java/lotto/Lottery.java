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

    public int getMatchCount(Lottery lottery) {
        return (int) lottery.lotteryNumbers.stream().filter(this::contains).count();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Lottery)) return false;

        Lottery cp = (Lottery) obj;

        return this.lotteryNumbers.equals(cp.lotteryNumbers);
    }
}
