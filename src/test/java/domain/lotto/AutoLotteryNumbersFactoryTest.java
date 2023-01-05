package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class AutoLotteryNumbersFactoryTest {
    @DisplayName("AutoLotteryNumber가 자동으로 LotteryNumbers를 생성한다")
    @Test
    void generate() {
        AutoLotteryNumbersFactory autoLotteryNumbersFactory = new AutoLotteryNumbersFactory();

        Assertions.assertThatNoException().isThrownBy(autoLotteryNumbersFactory::generate);
    }

    @DisplayName("생성된 LotteryNumbers는 서로 동일하지 않다")
    @RepeatedTest(1000)
    void duplicateTest() {
        AutoLotteryNumbersFactory autoLotteryNumbersFactory = new AutoLotteryNumbersFactory();

        Assertions.assertThat(autoLotteryNumbersFactory.generate())
                .isNotEqualTo(autoLotteryNumbersFactory.generate());
    }
}
