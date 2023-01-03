package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LotteryTest {
    @DisplayName("로또 숫자가 6개인지 확인한다")
    @Test
    void lotterySizeTest() {
        //given
        Lottery lottery = new Lottery();
        //when
        List<LotteryNumber> lotteryNumbers = lottery.getLotteryNumber();
        //then
        Assertions.assertThat(lotteryNumbers).hasSize(6);
    }
}
