package lotto.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomGenerator {
    public final List<Integer> generationRange;

    public RandomGenerator(List<Integer> generationRange) {
        this.generationRange = generationRange;
    }

    public List<Integer> getOrderedNumbers(int quantity) {
        Collections.shuffle(this.generationRange);
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            numbers.add(this.generationRange.get(i));
        }
        Collections.sort(numbers);
        return numbers;
    }
}
