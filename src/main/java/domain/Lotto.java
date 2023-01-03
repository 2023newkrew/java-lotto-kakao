package domain;

import common.constant.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Lotto {

    public static final int START_INDEX = 0;
    public static final List<Integer> WHOLE_NUMBERS = IntStream.rangeClosed(Constants.MINIMUM, Constants.MAXIMUM)
            .boxed()
            .collect(Collectors.toList());

    private final List<Integer> numbers;

    public Lotto() {
        Collections.shuffle(WHOLE_NUMBERS);
        List<Integer> numbers = WHOLE_NUMBERS.subList(START_INDEX, START_INDEX + Constants.LENGTH);
        Collections.sort(numbers);
        this.numbers = Collections.unmodifiableList(numbers);
    }

}
