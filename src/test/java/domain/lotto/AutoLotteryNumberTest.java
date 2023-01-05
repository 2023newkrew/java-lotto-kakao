package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AutoLotteryNumberTest {
    @DisplayName("AutoLotteryNumber가 자동으로 LotteryNumbers를 생성한다")
    @Test
    void generate() {
        AutoLotteryNumber autoLotteryNumber = new AutoLotteryNumber();

        Assertions.assertThatNoException().isThrownBy(autoLotteryNumber::generate);
    }

}
