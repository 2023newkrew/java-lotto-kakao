package buyer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BudgetTest {
    @DisplayName("Budget 감소 메서드 확인")
    @Test
    void decreaseBudget(){
        //given
        Budget budget = new Budget(2000);
        //when
        budget.decreaseBudget(100);
        //then
        Assertions.assertThat(budget.getBudget()).isEqualTo(1900);
    }

    @DisplayName("충분한 budget 없을 때 decrease 시 RuntimeException")
    @Test
    void decreaseBudget_invalid(){
        //given
        Budget budget = new Budget(2000);
        //when
        //then
        Assertions.assertThatRuntimeException().isThrownBy(() -> budget.decreaseBudget(3000));
    }

}
