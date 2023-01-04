package lotto;

import lotto.domain.LottoNumber;
import lotto.util.RandomNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;


class GeneratorTest {
    @RepeatedTest(1000)
    @DisplayName("start to end까지 n개의 숫자 목록을 생성한다")
    void generator() {
        for (int n = LottoNumber.MIN_NUMBER; n <= LottoNumber.MAX_NUMBER; n++) {
            assertThat(RandomNumberGenerator.getRandomNumbers(n, LottoNumber.MIN_NUMBER, LottoNumber.MAX_NUMBER)).hasSize(n);
        }

    }
}
