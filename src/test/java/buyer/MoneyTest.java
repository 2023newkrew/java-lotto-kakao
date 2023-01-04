package buyer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {
    @DisplayName("Money 감소 메서드 확인")
    @Test
    void decreaseBudget(){
        //given
        Money money = new Money(2000);
        //when
        money.decreaseMoney(new Money(100));
        //then
        Assertions.assertThat(money).isEqualTo(new Money(1900));
    }

    @DisplayName("충분한 budget 없을 때 decrease 시 RuntimeException")
    @Test
    void decreaseBudget_invalid(){
        //given
        Money money = new Money(2000);
        //when
        //then
        Assertions.assertThatRuntimeException().isThrownBy(() -> money.decreaseMoney(new Money(3000)));
    }

}
