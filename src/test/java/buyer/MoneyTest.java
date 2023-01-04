package buyer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @DisplayName("0 이상의 정수는 생성 성공")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10, 200, 3000})
    void moneyTest_valid(int number) {
        //given
        //when
        //then
        Assertions.assertThatNoException().isThrownBy(() -> new Money(number));
    }

    @DisplayName("0 미만의 정수는 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -200, -3000})
    void moneyTest_invalid(int number) {
        //given
        //when
        //then
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new Money(number));
    }

}
