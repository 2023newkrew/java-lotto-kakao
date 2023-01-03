package lotto;

import java.util.List;
import java.util.stream.Collectors;

public class Lottery {
    private final LotteryNumbers lotteryNumbers;

    public Lottery(List<Integer> numbers) {
        this.lotteryNumbers = new LotteryNumbers(numbers);
    }

    public boolean contains(LotteryNumber lotteryNumber) {
        return lotteryNumbers.contains(lotteryNumber);
    }

    public int getMatchCount(Lottery lottery) {
        return (int) lottery.lotteryNumbers.stream().filter(this::contains).count();
    }

    @Override
    public String toString() {
        return "[" +
                this.lotteryNumbers.stream()
                        .map(lotteryNumber -> Integer.toString(lotteryNumber.getNumber()))
                        .collect(Collectors.joining(",")) +
                "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Lottery)) return false;

        Lottery cp = (Lottery) obj;

        return this.lotteryNumbers.equals(cp.lotteryNumbers);
    }
}
