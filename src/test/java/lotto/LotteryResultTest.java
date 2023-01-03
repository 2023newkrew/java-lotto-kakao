package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LotteryResultTest {
    @DisplayName("당첨 번호 입력 테스트")
    @Test
    void matchTest() {
        LotteryResult lotteryResult = new LotteryResult(List.of(1, 2, 3, 4, 5, 6), 7);

        assertThat(lotteryResult).isEqualTo(new LotteryResult(List.of(1, 2, 3, 4, 5, 6), 7));
    }

    @DisplayName("중복된 당첨 번호 입력 테스트")
    @Test
    void duplicateTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new LotteryResult(List.of(1, 2, 3, 4, 5, 6), 6));
    }

    @DisplayName("로또 하나 받아서 통계 확인")
    @Test
    void singleStatistics() {
        LotteryResult lotteryResult = new LotteryResult(List.of(1, 2, 3, 4, 5, 6), 7);
        Lottery lottery = new Lottery(List.of(1, 2, 3, 9, 10, 11));

        assertThat(lotteryResult.getRank(lottery)).isEqualTo(Rank.FIFTH);
    }
}
