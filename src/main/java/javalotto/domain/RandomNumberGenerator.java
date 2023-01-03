package javalotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumberGenerator implements NumberGenerator {
    private RandomNumberGenerator() {
    }

    public static RandomNumberGenerator create() {
        return new RandomNumberGenerator();
    }

    @Override
    public List<Integer> generateNumbers(int minInclusive, int maxExclusive, int count) {
        List<Integer> numbers = IntStream.range(minInclusive, maxExclusive)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(numbers);

        return numbers.subList(0, count);
    }
}
