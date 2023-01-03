package lotto.util;

import java.util.Collections;
import java.util.List;

public class RandomGenerator {
    public final List<Integer> generationRange;

    public RandomGenerator(List<Integer> generationRange) {
        this.generationRange = generationRange;
    }

    public List<Integer> getOrderedNumbers(int quantity) {
        Collections.shuffle(this.generationRange);
        List<Integer> picked = generationRange.subList(0, quantity);
        Collections.sort(picked);
        return picked;
    }
}
