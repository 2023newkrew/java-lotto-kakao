package buyer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BuyerProfitTest {
    @DisplayName("수익률 테스트")
    @Test
    void profitTest() {
        BuyerProfit buyerProfit = new BuyerProfit(10, 20000);
        assertThat(buyerProfit).isEqualTo(new BuyerProfit((double) 20000 / 10000));
    }


}
