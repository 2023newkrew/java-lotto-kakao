package buyer;

import lotto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BuyerProfitTest {
    @DisplayName("수익률 테스트")
    @Test
    void profitTest() {
        //given
        Lotteries lotteries = new Lotteries();
        lotteries.addLottery(new Lottery(List.of(1, 2, 3, 4, 5, 6)));
        LotteryResult lotteryResult = new LotteryResult(List.of(1, 2, 3, 4, 5, 6), 8);
        //when
        BuyerResult buyerResult = lotteryResult.getResult(lotteries);
        //then

        assertThat(buyerResult.getProfit()).isEqualTo(new BuyerProfit((double) Rank.FIRST.prize / 1000));

    }
}
