package lotto.domain;

import static lotto.constant.ExceptionMessage.NON_EXISTENT_LOTTERY_COUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.domain.lotterynumber.LotteryNumberCombination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LotteryVendingMachineTest {
    @DisplayName("0 미만의 자동 구매 요청 시 예외가 발생한다.")
    @Test
    void SellQuickPicksWrongCountTest() {
        assertThatThrownBy(() -> LotteryVendingMachine.sellQuickPicks(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NON_EXISTENT_LOTTERY_COUNT);
    }

    @DisplayName("0 이상의 자동 구매 요청 시 해당 개수만큼의 로또를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 10, 1_000, 10_000})
    void SellQuickPicksTest(int count) {
        assertThat(LotteryVendingMachine.sellQuickPicks(count))
                .hasSize(count)
                .doesNotContainNull();
    }

    @DisplayName("로또 숫자들의 List를 활용하여 로또를 생성한다.")
    @Test
    void SellSelfPicksTest() {
        List<Integer> selfPickNumber1 = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> selfPickNumber2 = List.of(7, 9, 11, 2, 3, 4);
        List<List<Integer>> selfPickNumbers = List.of(selfPickNumber1, selfPickNumber2);

        assertThat(LotteryVendingMachine.sellSelfPicksWith(selfPickNumbers).stream()
                .map(LotteryNumberCombination::toString))
                .containsExactly(new LotteryNumberCombination(selfPickNumber1).toString(),
                        new LotteryNumberCombination(selfPickNumber2).toString());
    }
}
