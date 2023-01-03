package buyer;

import lotto.Lottery;
import lotto.LotteryResult;
import lotto.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BuyerProfitTest {
    @DisplayName("수익률 테스트")
    @Test
    void profitTest() {
        //given
        Buyer buyer = new Buyer(1000);
        buyer.buyLottery(1000, new Lottery(List.of(1, 2, 3, 4, 5, 6)));
        LotteryResult lotteryResult = new LotteryResult(List.of(1, 2, 3, 4, 5, 6), 8);
        //when
        BuyerResult buyerResult = buyer.getBuyerResult(lotteryResult);
        //then
        assertThat(buyerResult.getProfit()).isEqualTo(new BuyerProfit((double) Rank.FIRST.prize / 1000));
    }


}
