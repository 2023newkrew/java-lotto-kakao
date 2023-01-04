package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LotteryResultTest {
    @DisplayName("중복되지 않는 numbers와 bonusNumbers를 받아 LotteryResult를 생성한다")
    @Test
    void lotteryResultTest() {
        assertThatNoException().isThrownBy(() -> new LotteryResult(List.of(1, 2, 3, 4, 5, 6), 7));
    }

    @DisplayName("bonusNumber에 중복된 숫자를 받으면 예외를 발생한다")
    @Test
    void duplicateTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new LotteryResult(List.of(1, 2, 3, 4, 5, 6), 6));
    }

    @DisplayName("Lottery를 받아 lotteryResult 기준으로 Rank를 반환한다")
    @Test
    void getRank() {
        LotteryResult lotteryResult = new LotteryResult(List.of(1, 2, 3, 4, 5, 6), 7);
        Lottery lottery = new Lottery(List.of(1, 2, 3, 9, 10, 11));

        assertThat(lotteryResult.getRank(lottery)).isEqualTo(Rank.FIFTH);
    }
}
