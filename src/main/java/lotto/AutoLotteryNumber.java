package lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLotteryNumber {
    private List<Integer> numberCollection = IntStream.range(1, 46)
            .boxed()
            .collect(Collectors.toList());

    public LotteryNumbers generate() {
        Collections.shuffle(numberCollection);

        return new LotteryNumbers(numberCollection.subList(0, 6));
    }
}
