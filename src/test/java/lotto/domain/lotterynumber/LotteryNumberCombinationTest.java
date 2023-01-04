package lotto.domain.lotterynumber;

import static lotto.constant.ExceptionMessage.LOTTERY_NUMBER_OUT_OF_RANGE;
import static lotto.constant.ExceptionMessage.LOTTO_NUMBERS_WRONG_DISTINCT_COUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LotteryNumberCombinationTest {
    @DisplayName("1과 45 사이의 값을 포함한 List로 생성하면 예외가 발생한다.")
    @Test
    void LotteryNumberCombinationRangeExceptionTest() {
        assertThatThrownBy(() -> new LotteryNumberCombination(Arrays.asList(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTERY_NUMBER_OUT_OF_RANGE);
    }

    @DisplayName("입력의 크기가 6이 아닌 경우 예외가 발생한다.")
    @Test
    void LotteryNumberCombinationSizeTest() {
        assertAll(
                () -> assertThatThrownBy(() -> new LotteryNumberCombination(Arrays.asList(1, 2, 3, 4, 5)))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(LOTTO_NUMBERS_WRONG_DISTINCT_COUNT),
                () -> assertThatThrownBy(() -> new LotteryNumberCombination(
                        Stream.of(1, 2, 3, 4, 5, 6, 7).map(LotteryNumber::of).collect(
                                Collectors.toSet())))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(LOTTO_NUMBERS_WRONG_DISTINCT_COUNT)
        );
    }

    @DisplayName("LotteryNumberCombination이 입력값을 포함하는지 반환한다.")
    @Test
    void LotteryNumberContainsTest() {
        LotteryNumberCombination lotteryTicket = new LotteryNumberCombination(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertAll(
                () -> assertThat(lotteryTicket.contains(1)).isTrue(),
                () -> assertThat(lotteryTicket.contains(8)).isFalse(),
                () -> assertThat(lotteryTicket.contains(LotteryNumber.of(1))).isTrue(),
                () -> assertThat(lotteryTicket.contains(LotteryNumber.of(8))).isFalse()
        );
    }

    @DisplayName("주어진 LotteryNumberCombination과 일치하는 개수를 반환한다.")
    @Test
    void LotteryNumberMatchCountTest() {
        LotteryNumberCombination lotteryTicket = new LotteryNumberCombination(Arrays.asList(1, 2, 3, 4, 5, 6));
        LotteryNumberCombination otherTicket = new LotteryNumberCombination(Arrays.asList(1, 2, 3, 7, 8, 9));
        assertAll(
                () -> assertThat(lotteryTicket.calculateMatchCount(lotteryTicket)).isEqualTo(6),
                () -> assertThat(lotteryTicket.calculateMatchCount(otherTicket)).isEqualTo(3),
                () -> assertThat(otherTicket.calculateMatchCount(lotteryTicket)).isEqualTo(3),
                () -> assertThat(otherTicket.calculateMatchCount(otherTicket)).isEqualTo(6)
        );
    }
}
