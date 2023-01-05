package lotto.model.store;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

public class MoneyTest {

    @Nested
    class valueOf{

        @DisplayName("amount가 0원 미만일 때 예외를 발생")
        @ParameterizedTest
        @ValueSource(longs = {-1L, -100L, -1000L})
        void should_throwException_when_AmountUnderZero(long amount){
            Assertions.assertThatThrownBy(() -> Money.valueOf(amount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("돈은 0원 이상입니다.");
        }

        @DisplayName("amount 가지고 있는 Money를 생성")
        @ParameterizedTest
        @ValueSource(longs = {0L, 1000L, 9999L})
        void should_returnMoneyHasValue_when_valueOfAmount(long amount){
            Money money = Money.valueOf(amount);

            Assertions.assertThat(money.bigDecimal()).isEqualTo(BigDecimal.valueOf(amount));
        }
    }

    @Nested
    class divide{

        @DisplayName("0으로 나눌 경우 예외를 발생")
        @Test
        void should_throwException_when_divideZero(){
            Assertions.assertThatThrownBy(() -> Money.ZERO.divide(Money.ZERO))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("0으로 나눌 수 없습니다.");
        }

        @DisplayName("1000원을 divider로 나눗셈")
        @ParameterizedTest
        @CsvSource({"1,1000","2,500","3,333","400,2","1000,1","1001,0"})
        void should_returnMoneyHasValue_when_valueOfAmount(long divider, long expected){
            Money money = Money.valueOf(1000);
            Money dividerMoney = Money.valueOf(divider);

            Assertions.assertThat(money.divide(dividerMoney)).isEqualTo(BigDecimal.valueOf(expected));
        }
    }
}