package lotto;

import lotto.util.RandomNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;


class GeneratorTest {
    @RepeatedTest(10000)
    @DisplayName("start to end까지 n개의 숫자 목록을 생성한다")
    void generator() {
        assertThat(RandomNumberGenerator.getRandomNumbers(6, 1, 45)).hasSize(6);
    }
}
