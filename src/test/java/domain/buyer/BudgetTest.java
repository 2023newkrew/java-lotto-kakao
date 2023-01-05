package domain.buyer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BudgetTest {
    @DisplayName("Budget 생성자에서 음수를 받으면 예외를 발생한다")
    @Test
    void minus() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new Budget(-1));

    }

    @DisplayName("Budget으로 구매할 수 있는 개수 반환한다")
    @Test
    void getAbleLotteryCount() {
        Budget budget = new Budget(12000);

        Assertions.assertThat(budget.getAbleLotteryCount(1000)).isEqualTo(12);
    }
}
