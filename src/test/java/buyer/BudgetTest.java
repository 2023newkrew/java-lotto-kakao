package buyer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatRuntimeException;

public class BudgetTest {
    @DisplayName("Budget의 값을 number만큼 줄인다")
    @Test
    void decreaseBudget() {
        Budget budget = new Budget(2000);

        budget.decreaseBudget(100);
        assertThat(budget.getBudget()).isEqualTo(1900);
    }

    @DisplayName("Budget보다 큰 number을 줄일 때 예외를 발생한다")
    @Test
    void decreaseBudget_invalid() {
        Budget budget = new Budget(2000);

        assertThatRuntimeException().isThrownBy(() -> budget.decreaseBudget(3000));
    }

}
