package lotto.domain.lotterynumber;

import static lotto.constant.ExceptionMessage.LOTTERY_NUMBER_OUT_OF_RANGE;
import static lotto.constant.LotteryConstant.LOTTO_NUMBER_MAXIMIM;
import static lotto.constant.LotteryConstant.LOTTO_NUMBER_MINIMUM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LotteryNumberTest {
    private final int[] validNumbers = IntStream.range(LOTTO_NUMBER_MINIMUM, LOTTO_NUMBER_MAXIMIM).toArray();

    @DisplayName("1과 45 사이의 값이 아닌 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 500})
    void LotteryNumberRangeExceptionTest(int number) {
        assertThatThrownBy(() -> LotteryNumber.of(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTERY_NUMBER_OUT_OF_RANGE);
    }

    @DisplayName("1과 45 사이의 값인 경우 예외가 발생하지 않는다.")
    @Test
    void LotteryNumberConstructTest() {
        for (int number : validNumbers) {
            assertThatCode(() -> LotteryNumber.of(number))
                    .doesNotThrowAnyException();
        }
    }

    @DisplayName("같은 값에 대해서는 하나의 인스턴스를 생성한다.")
    @Test
    void LotteryNumberIdentityTest() {
        for (int number : validNumbers) {
            assertThat(LotteryNumber.of(number)).isEqualTo(LotteryNumber.of(number));
        }
    }

    @DisplayName("LotteryNumber간 값 비교를 할 수 있다.")
    @Test
    void LotteryNumberCompareTest() {
        int referenceNumber = 20;
        for (int number : validNumbers) {
            assertThat(LotteryNumber.of(number).compareTo(LotteryNumber.of(referenceNumber)))
                    .isEqualTo(Integer.compare(number, referenceNumber));
        }
    }
}
