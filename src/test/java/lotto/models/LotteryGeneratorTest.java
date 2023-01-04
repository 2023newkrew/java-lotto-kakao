package lotto.models;

import static lotto.common.LotteryConfiguration.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.common.LotteryGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LotteryGeneratorTest {

    @Test
    @DisplayName("자동으로 로또를 생성할 수 있다.")
    public void testCreateLottery() {
        List<Integer> lottery = LotteryGenerator.createLottery().getNumbers();
        assertThat(lottery).hasSize(6);
        assertThat(lottery).isSorted();
        for (Integer number : lottery) {
            assertThat(number).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(45);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000})
    @DisplayName("자동으로 다수의 로또를 생성할 수 있다.")
    public void testCreateLotteries(Integer input) {
        List<Lottery> lotteries = LotteryGenerator.createLotteries(input);
        assertThat(lotteries).hasSize(input);
        lotteries.forEach((lottery -> assertThat(lottery.getNumbers()).hasSize(LOTTERY_COUNT)));
    }
}