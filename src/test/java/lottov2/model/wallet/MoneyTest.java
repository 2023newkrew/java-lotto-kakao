package lottov2.model.wallet;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

            Assertions.assertThat(money.longValue()).isEqualTo(amount);
        }
    }
}