package domain.buyer;

import domain.lotto.Lottery;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BuyerTest {
    @DisplayName("Buyer가 구매할 수 있는 양보다 많은 buy를 수행하면 예외 발생")
    @Test
    void buy() {
        Assertions.assertThatIllegalStateException().isThrownBy(this::buyOverPlan);
    }

    private void buyOverPlan() {
        Buyer buyer = new Buyer(2000, 0);

        buyer.buyLottery(new Lottery(List.of(1, 2, 3, 4, 5, 6)));
        buyer.buyLottery(new Lottery(List.of(1, 2, 3, 4, 5, 6)));
        buyer.buyLottery(new Lottery(List.of(1, 2, 3, 4, 5, 6)));
    }

}
