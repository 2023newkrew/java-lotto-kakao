package lotto.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomGenerator {
    private final List<Integer> defaultRange;
    private static final int DEFAULT_LOWER_BOUND = 1;
    private static final int DEFAULT_UPPER_BOUND = 45;

    public RandomGenerator() {
        defaultRange = new ArrayList<>();
        for (int i = DEFAULT_LOWER_BOUND; i <= DEFAULT_UPPER_BOUND; i++) {
            defaultRange.add(i);
        }
    }

    public List<Integer> createNumbers(int count) {
        Collections.shuffle(defaultRange);
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            numbers.add(defaultRange.get(i));
        }

        return numbers;
    }

    public List<Integer> createNumbers(int count, int lowerBound, int upperBound) {
        List<Integer> range = new ArrayList<>();
        for (int i = lowerBound; i <= upperBound; i++) {
            range.add(i);
        }
        Collections.shuffle(range);
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            numbers.add(defaultRange.get(i));
        }

        return numbers;
    }
}
