package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LotteryNumbersTest {
    @DisplayName("LotteryNumbers에 중복된 숫자가 존재하면 예외를 발생한다")
    @Test
    void duplicateTest() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new LotteryNumbers(List.of(1, 1, 1, 1, 1, 1)));
    }

    @DisplayName("LotteryNumbers에 6개의 숫자가 없으면 예외를 발생한다")
    @Test
    void sizeTest() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new LotteryNumbers(List.of(1)));
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new LotteryNumbers(List.of(1, 2, 3, 4, 5, 6, 7)));
    }
}
