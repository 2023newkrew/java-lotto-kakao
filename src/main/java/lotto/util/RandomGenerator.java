package lotto.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomGenerator {
    public final List<Integer> defaultRange;

    public RandomGenerator() {
        defaultRange = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            defaultRange.add(i);
        }
    }

    public List<Integer> createNumbers(int count) {
        Collections.shuffle(defaultRange);
        return defaultRange.subList(0, count);
    }

    public List<Integer> createNumbers(int count, int lowerBound, int upperBound) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = lowerBound; i <= upperBound; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers.subList(0, count);
    }
}
