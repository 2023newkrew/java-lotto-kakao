package buyer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BuyerProfitTest {
    @DisplayName("lotteryCount와 totalPrize를 받아 총 수익률을 계산한다")
    @Test
    void profitTest() {
        BuyerProfit buyerProfit = new BuyerProfit(10, 20000);
        assertThat(buyerProfit).isEqualTo(new BuyerProfit((double) 20000 / 10000));
    }


}
