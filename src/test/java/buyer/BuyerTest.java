package buyer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BuyerTest {
    @DisplayName("Buyer의 budget으로 구매할 수 있는 Lottery 개수 반환")
    @Test
    void getAbleLotteryCount() {
        Buyer buyer = new Buyer(12000);

        Assertions.assertThat(buyer.getAbleLotteryCount()).isEqualTo(12);
    }
}
