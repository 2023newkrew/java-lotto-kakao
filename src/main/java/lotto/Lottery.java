package lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lottery {
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

    public List<LotteryNumber> getLotteryNumber() {
        return lotteryNumbers.getNumbers();
    }
}
