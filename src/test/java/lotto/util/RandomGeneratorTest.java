package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RandomGeneratorTest {
    @Test
    @DisplayName("can get ordered numbers of given size in the range")
    void can_get_ordered_numbers_of_given_size_in_the_range() {
        List<Integer> generationRange = List.of(1, 3, 5, 7, 9, 11, 13, 15, 17, 19);
        RandomGenerator randomGenerator = new RandomGenerator(generationRange);

        int cycle = 1000;
        int size = 6;
        List<Integer> numbers;
        for (int i = 0; i < cycle; i++) {
            numbers = randomGenerator.getOrderedNumbers(size);
            assertEquals(size, numbers.size());
            for (int j = 1; j < size; j++) {
                assertTrue(numbers.get(j) > numbers.get(j-1));
            }
        }
    }
}
