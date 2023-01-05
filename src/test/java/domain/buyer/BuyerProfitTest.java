package domain.buyer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BuyerProfitTest {
    @DisplayName("lotteryCount와 totalPrize를 받아 총 수익률을 계산한다")
    @Test
    void profitTest() {
        BuyerProfit buyerProfit = new BuyerProfit(10, 20000);
        assertThat(buyerProfit.getProfit()).isEqualTo(2);
    }

    @DisplayName("lotteryCount가 0이면 총 수익을 0으로 한다")
    @Test
    void zeroCount() {
        BuyerProfit buyerProfit = new BuyerProfit(0, 0);

        assertThat(buyerProfit.getProfit()).isZero();
    }
}
