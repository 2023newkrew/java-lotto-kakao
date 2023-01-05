package domain.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LotteryTest {
    @DisplayName("다른 Lottery를 받아 매칭되는 LotteryNumber의 개수를 반환한다")
    @Test
    void matchCount() {
        Lottery lottery = new Lottery(List.of(1, 2, 3, 4, 5, 6));
        Lottery cpLottery = new Lottery(List.of(1, 2, 7, 8, 9, 10));

        Assertions.assertThat(lottery.getMatchCount(cpLottery)).isEqualTo(2);
    }

    @DisplayName("LotteryNumbeers를 받아 Lottery를 생성한다")
    @Test
    void lotteryWithLotteryNumbers() {
        LotteryNumbers lotteryNumbers = new LotteryNumbers(List.of(1, 2, 3, 4, 5, 6));

        Assertions.assertThatNoException().isThrownBy(() -> new Lottery(lotteryNumbers));
    }
}
