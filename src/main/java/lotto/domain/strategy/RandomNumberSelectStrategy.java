package lotto.domain.strategy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomNumberSelectStrategy implements NumberSelectStrategy {

    private static final int NUMBER_MIN_BOUND = 1;
    private static final int NUMBER_MAX_BOUND = 45;
    private static final int NUMBER_LIMIT = 6;

    @Override
    public List<Integer> select() {
        List<Integer> numbers = Stream.iterate(NUMBER_MIN_BOUND, n -> n + 1)
                .limit(NUMBER_MAX_BOUND - NUMBER_MIN_BOUND + 1)
                .collect(Collectors.toList());
        Collections.shuffle(numbers);
        return numbers.subList(0, NUMBER_LIMIT).stream()
                .sorted().collect(Collectors.toList());
    }
}
