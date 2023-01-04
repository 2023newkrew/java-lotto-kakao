package buyer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {
    @DisplayName("Budget 감소 메서드 확인")
    @Test
    void decreaseBudget(){
        //given
        Money budget = new Money(2000);
        //when
        budget.decreaseMoney(100);
        //then
        Assertions.assertThat(budget.getMoney()).isEqualTo(1900);
    }

    @DisplayName("충분한 budget 없을 때 decrease 시 RuntimeException")
    @Test
    void decreaseBudget_invalid(){
        //given
        Money budget = new Money(2000);
        //when
        //then
        Assertions.assertThatRuntimeException().isThrownBy(() -> budget.decreaseMoney(3000));
    }

}
