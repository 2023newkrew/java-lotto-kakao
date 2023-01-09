package lotto.domain.lotterynumber;

import static lotto.constant.ExceptionMessage.NON_EXISTENT_LOTTERY_COUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RandomLotteryNumberCombinationFactoryTest {
    @DisplayName("팩토리의 create 기능 시 정상적으로 LotteryNumberCombination을 생성한다.")
    @RepeatedTest(100)
    void randomLotteryNumberCombinationFactoryCreateTest() {
        assertThat(RandomLotteryNumberCombinationFactory.create())
                .isInstanceOf(LotteryNumberCombination.class);
    }

    @DisplayName("팩토리의 createMany 기능 시 0 미만의 개수에 대해서는 예외가 발생한다.")
    @Test
    void randomLotteryNumberCombinationFactoryCreateManyWrongCountTest() {
        assertThatThrownBy(() -> RandomLotteryNumberCombinationFactory.createMany(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NON_EXISTENT_LOTTERY_COUNT);
    }

    @DisplayName("팩토리의 createMany 기능 시 정상적으로 LotteryNumberCombination의 리스트를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 20})
    void randomLotteryNumberCombinationFactoryCreateManyTest(int count) {
        assertThat(RandomLotteryNumberCombinationFactory.createMany(count))
                .hasSize(count)
                .doesNotContainNull();
    }
}
