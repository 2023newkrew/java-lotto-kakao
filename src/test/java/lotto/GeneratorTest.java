package lotto;

import lotto.config.LottoConfig;
import lotto.util.RandomNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;


class GeneratorTest {
    @RepeatedTest(1000)
    @DisplayName("start to end까지 n개의 숫자 목록을 생성한다")
    void generator() {
        for (int n = LottoConfig.MIN_NUMBER; n <= LottoConfig.MAX_NUMBER; n++) {
            assertThat(RandomNumberGenerator.get(n, LottoConfig.MIN_NUMBER, LottoConfig.MAX_NUMBER)).hasSize(n);
        }

    }
}
