package lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lottery {
    private final LotteryNumber lotteryNumber;
    private final List<Integer> numberCollection = IntStream.range(1,46)
            .boxed()
            .collect(Collectors.toList());

    public Lottery() {
        Collections.shuffle(numberCollection);
        lotteryNumber = new LotteryNumber(numberCollection.subList(0, 6));

    }

    public Lottery(List<Integer> numbers) {
        this.lotteryNumber = new LotteryNumber(numbers);
    }

    public List<Integer> getLotteryNumber() {
        return lotteryNumber.getNumbers();
    }
}
