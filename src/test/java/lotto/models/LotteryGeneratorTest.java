package lotto.models;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Objects;
import lotto.common.LotteryGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LotteryGeneratorTest {

    @Test
    @DisplayName("자동으로 로또를 생성할 수 있다.")
    public void testCreateLotteryAuto() {
        Lottery lottery = LotteryGenerator.createLotteryAuto();
        assertThat(lottery.getNumbers()).hasSize(6);
        assertThat(lottery.getNumbers()).isSortedAccordingTo((prev, next) -> {
            if (Objects.equals(prev.getNumber(), next.getNumber()))
                return 0;
            if (prev.getNumber() > next.getNumber())
                return 1;
            return -1;
        });
    }

    @Test
    @DisplayName("수동으로 로또를 생성할 수 있다.")
    public void testCreateLotteryManual() {
        Lottery lottery = LotteryGenerator.createLotteryManual(List.of(2,1,4,3,6,5));
        assertThat(lottery.getNumbers()).containsExactly(
                LotteryNumber.from(1),
                LotteryNumber.from(2),
                LotteryNumber.from(3),
                LotteryNumber.from(4),
                LotteryNumber.from(5),
                LotteryNumber.from(6)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000})
    @DisplayName("자동으로 다수의 로또를 생성할 수 있다.")
    public void testCreateLotteries(Integer input) {
        List<Lottery> lotteries = LotteryGenerator.createLotteries(input);
        assertThat(lotteries).hasSize(input);
    }
}