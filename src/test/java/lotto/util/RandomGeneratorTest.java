package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class RandomGeneratorTest {

    @Test
    @DisplayName("로또의 숫자는 랜덤으로 생성된다.")
    void createNumbersWithDefaultRangeTest() {
        RandomGenerator randomGenerator = new RandomGenerator();
        List<Integer> numbers = randomGenerator.createNumbers(6);

        for (Integer number : numbers) {
            assertThat(number).isGreaterThan(0)
                    .isLessThan(46);
        }
    }

    @Test
    @DisplayName("로또의 숫자는 랜덤으로 생성된다.")
    void createNumbersWithCustomRangeTest() {
        RandomGenerator randomGenerator = new RandomGenerator();
        List<Integer> numbers = randomGenerator.createNumbers(6, -5, 99);

        for (Integer number : numbers) {
            assertThat(number).isGreaterThan(-6)
                    .isLessThan(100);
        }
    }
}
