package buyer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatRuntimeException;

public class BudgetTest {
    @DisplayName("Budget 감소 메서드 확인")
    @Test
    void decreaseBudget(){
        Budget budget = new Budget(2000);

        budget.decreaseBudget(100);
        assertThat(budget.getBudget()).isEqualTo(1900);
    }

    @DisplayName("충분한 budget 없을 때 decrease 시 RuntimeException")
    @Test
    void decreaseBudget_invalid(){
        Budget budget = new Budget(2000);

        assertThatRuntimeException().isThrownBy(() -> budget.decreaseBudget(3000));
    }

}
