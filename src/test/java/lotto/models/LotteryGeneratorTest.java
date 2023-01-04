package lotto.models;

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
        Lottery lottery = LotteryGenerator.createLottery();
        assertThat(lottery.getNumbers()).hasSize(6);
        assertThat(lottery.getNumbers()).isSorted();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000})
    @DisplayName("자동으로 다수의 로또를 생성할 수 있다.")
    public void testCreateLotteries(Integer input) {
        List<Lottery> lotteries = LotteryGenerator.createLotteries(input);
        assertThat(lotteries).hasSize(input);
    }
}