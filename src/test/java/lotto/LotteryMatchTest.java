package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LotteryMatchTest {
    @DisplayName("matchCount와 bonusMatch를 입력받아 LotteryMatchf를 생성한다")
    @Test
    void matchTest() {
        LotteryMatch lotteryMatch = new LotteryMatch(3, true);

        assertThat(lotteryMatch).isEqualTo(new LotteryMatch(3, true));
    }

}
