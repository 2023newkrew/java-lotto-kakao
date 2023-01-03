package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LotteryTest {
    @DisplayName("Lottery의 MatchCount 테스트")
    @Test
    void matchCount() {
        Lottery lottery = new Lottery(List.of(1, 2, 3, 4, 5, 6));
        Lottery cpLottery = new Lottery(List.of(1, 2, 7, 8, 9, 10));

        Assertions.assertThat(lottery.getMatchCount(cpLottery)).isEqualTo(2);
    }
}
