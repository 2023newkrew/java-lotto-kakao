package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LotteryMatchTest {
    @DisplayName("매칭 개수 확인 테스트")
    @Test
    void matchTest(){
        LotteryMatch lotteryMatch = new LotteryMatch(3,true);

        assertThat(lotteryMatch).isEqualTo(new LotteryMatch(3, true));
    }

}
