package lotto.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;

class RandomNumbersGeneratorTest {
    RandomNumbersGenerator randomNumbersGenerator = new RandomNumbersGenerator();
    List<Integer> randomNumbers;

    @BeforeEach
    void setUp() {
        randomNumbers = randomNumbersGenerator.generateRandomNumbers();
    }

    @Test
    void checkSixSize() {
        Assertions.assertEquals(randomNumbers.size(), 6);
    }

    @RepeatedTest(100)
    void isInRange() {
        for (int i = 0; i < 6; i++) {
            Assertions.assertTrue(randomNumbers.get(i) >= 1 && randomNumbers.get(i) <= 45);
        }
    }
}