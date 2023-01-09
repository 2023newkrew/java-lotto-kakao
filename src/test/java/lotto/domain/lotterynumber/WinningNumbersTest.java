package lotto.domain.lotterynumber;

import static lotto.constant.ExceptionMessage.LOTTERY_NUMBER_OUT_OF_RANGE;
import static lotto.constant.ExceptionMessage.BONUS_NUMBER_DUPLICATED;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {
    private LotteryNumberCombination winningLotteryNumberCombination;

    @BeforeEach
    void initialize() {
        winningLotteryNumberCombination = new LotteryNumberCombination(List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("범위에 벗어난 Bonus Number를 전달하는 경우 예외가 발생한다.")
    @Test
    void invalidBonusNumberTest() {
        assertThatThrownBy(() -> new WinningNumbers(winningLotteryNumberCombination, -3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTERY_NUMBER_OUT_OF_RANGE);
    }

    @DisplayName("Bonus Number가 중복되는 경우 예외가 발생한다.")
    @Test
    void bonusNumberDuplicateTest() {
        assertThatThrownBy(() -> new WinningNumbers(winningLotteryNumberCombination, 3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_NUMBER_DUPLICATED);
    }

    @DisplayName("WinningNumbers를 정상적으로 생성한다")
    @Test
    void winningNumbersSuccessTest() {
        assertThatCode(() -> new WinningNumbers(winningLotteryNumberCombination, 7))
                .doesNotThrowAnyException();
        assertThatCode(() -> new WinningNumbers(winningLotteryNumberCombination, LotteryNumber.of(7)))
                .doesNotThrowAnyException();
    }
}
