package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 46;

    public static final List<Integer> WHOLE_NUMBERS = IntStream.range(LOWER_BOUND, UPPER_BOUND).boxed().collect(Collectors.toList());

    private final List<Integer> numbers;
    public Lotto() {
        Collections.shuffle(WHOLE_NUMBERS);
        List<Integer> numbers = WHOLE_NUMBERS.subList(0, 6);
        Collections.sort(numbers);
        this.numbers = Collections.unmodifiableList(numbers);
    }

}
