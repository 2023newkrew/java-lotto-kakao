package lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lottery {
    public static final int PRICE = 1000;

    private final LotteryNumbers lotteryNumbers;

    public Lottery() {
        List<Integer> numberCollection = IntStream.range(1, 46)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numberCollection);

        lotteryNumbers = new LotteryNumbers(numberCollection.subList(0, 6));
    }

    public Lottery(List<Integer> numbers) {
        this.lotteryNumbers = new LotteryNumbers(numbers);
    }

    public boolean contains(LotteryNumber lotteryNumber) {
        return lotteryNumbers.contains(lotteryNumber);
    }

    public int getMatchCount(Lottery lottery) {
        return (int) lottery.lotteryNumbers.stream().filter(this::contains).count();
    }

    public LotteryDTO toDTO() {
        return new LotteryDTO(lotteryNumbers.stream().map(LotteryNumber::getNumber).collect(Collectors.toList()));
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
