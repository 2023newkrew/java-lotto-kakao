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
        List<Integer> lotteryNumbers = lottery.getNumbers();
        //then
        Assertions.assertThat(lotteryNumbers).hasSize(6);
    }

    @DisplayName("모든 로또 숫자가 1부터 45 사이인지 확인")
    @Test
    void lotteryNumberTest() {
        //given
        Lottery lottery = new Lottery();
        //when
        List<Integer> lotteryNumbers = lottery.getNumbers();
        //then
        for (Integer lotteryNumber : lotteryNumbers) {
            Assertions.assertThat(lotteryNumber).isBetween(1, 45);
        }
    }
}
