package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LotteryMatchTest {
    @DisplayName("매칭 개수 확인 테스트")
    @Test
    void matchTest(){
        //given
        LotteryMatch lotteryMatch = new LotteryMatch(3,true);
        //when
        //then
        Assertions.assertThat(lotteryMatch).isEqualTo(new LotteryMatch(3, true));
    }

}
