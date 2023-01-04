package lotto.models;

import static lotto.common.LotteryConfiguration.LOTTERY_COUNT;
import static lotto.common.LotteryConfiguration.MAX_VALUE;
import static lotto.common.LotteryConfiguration.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LotteryTest {
    @Nested
    @DisplayName("유효한 로또 생성 테스트")
    public class CreateValidLottery {
        @Test
        @DisplayName("로또를 생성할 수 있다.")
        public void testCreateLottery() {
            Lottery lottery = new Lottery(Arrays.asList(4, 2, 3, 1, 5, 6));
            assertThat(lottery.getNumbers()).containsExactly(
                    LotteryNumber.from(1),
                    LotteryNumber.from(2),
                    LotteryNumber.from(3),
                    LotteryNumber.from(4),
                    LotteryNumber.from(5),
                    LotteryNumber.from(6)
            );
        }
    }

    @Nested
    @DisplayName("유효하지 않은 로또 생성 테스트")
    public class CreateInvalidLottery {
        @Test
        @DisplayName("숫자 수가 6개가 아닌 로또를 생성하면 예외가 발생한다.")
        public void testInvalidSizeLottery() {
            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> new Lottery(Arrays.asList(1, 2, 3, 4, 5)))
                    .withMessage("로또는" + LOTTERY_COUNT + "개의 숫자로 이루어져야 합니다.");
        }

        @Test
        @DisplayName("범위를 벗어난 숫자가 포함된 로또를 생성하면 예외가 발생한다.")
        public void testInvalidRangeLottery() {
            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> new Lottery(Arrays.asList(1, 2, 3, 4, 105, 6)))
                    .withMessage("로또는 " + MIN_VALUE + "부터" + MAX_VALUE + "사이의 수 이어야 합니다.");
        }

        @Test
        @DisplayName("중복된 숫자가 포함된 로또를 생성하면 에러가 발생한다.")
        public void testLotteryIncludeDuplicatedNumber() {
            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> new Lottery(Arrays.asList(1, 2, 3, 4, 5, 5)))
                    .withMessage("로또 번호에 중복된 번호가 있어서는 안됩니다.");
        }
    }
}