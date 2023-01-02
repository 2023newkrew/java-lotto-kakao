package lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lottery {
    private final List<Integer> numbers;
    private static final List<Integer> numberCollection = IntStream.range(1,46)
            .boxed()
            .collect(Collectors.toList());

    public Lottery() {
        Collections.shuffle(numberCollection);
        numbers = numberCollection.subList(0, 6);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
