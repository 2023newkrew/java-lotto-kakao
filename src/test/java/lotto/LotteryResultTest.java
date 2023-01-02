package lotto;

import buyer.BuyerResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LotteryResultTest {
    @DisplayName("당첨 번호 입력 테스트")
    @Test
    void matchTest(){
        LotteryResult lotteryResult = new LotteryResult(List.of(1,2,3,4,5,6), 7);

        assertThat(lotteryResult).isEqualTo(new LotteryResult(List.of(1,2,3,4,5,6), 7));
    }
    
    @DisplayName("로또 하나 받아서 통계 확인")
    @Test
    void singleStatistics(){
        LotteryResult lotteryResult = new LotteryResult(List.of(1,2,3,4,5,6), 7);
        Lottery lottery = new Lottery(List.of(1,2,3,9,10,11));

        assertThat(lotteryResult.getMatchCount(lottery)).isEqualTo(3);
    }

    @DisplayName("로또 뭉치에 대한 결과 객체 확인")
    @Test
    void lotteryListResult() {
        LotteryResult lotteryResult = new LotteryResult(List.of(1,2,3,4,5,6), 7);
        List<Lottery> lotteries = new ArrayList<>(
                List.of(new Lottery(List.of(1,2,3,12,13,14)))
        );
        BuyerResult buyerResult = lotteryResult.getResult(lotteries);
        assertThat(buyerResult).isEqualTo(new BuyerResult(List.of(1,0,0,0,0)));
    }
}
