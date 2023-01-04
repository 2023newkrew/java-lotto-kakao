package buyer;

import lotto.Lottery;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BuyingPlanTest {
    @DisplayName("budget으로 구매할 수 없는 manualCount를 받으면 예외 발생")
    @Test
    void buyingPlanException() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new BuyingPlan(1000, 3));
    }

    @DisplayName("budget으로 구매할 수 있는 manualCount를 받아 manualCount와 autoCount의 합을 budget으로 구매할 수 있는 숫자로 초기화")
    @Test
    void buyingPlan() {
        BuyingPlan buyingPlan = new BuyingPlan(30500, 10);

        Assertions.assertThat(buyingPlan.getTotalCount()).isEqualTo(30500 / Lottery.PRICE);
    }
}
