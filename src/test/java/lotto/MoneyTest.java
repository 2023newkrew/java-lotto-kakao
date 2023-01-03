package lotto;

import lotto.model.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


public class MoneyTest {

    @Nested
    class valueOf{

        @DisplayName("value가 0원 이하일 때 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(longs = {0L, -1L, -100L})
        void should_throwException_when_AmountUnderZero(long amount){
            Assertions.assertThatThrownBy(() -> Money.valueOf(amount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("돈은 0원을 초과해야 합니다.");
        }
    }

    @Nested
    class getPurchasableCount{
        @DisplayName("가격만큼 살 수 있는 숫자를 반환한다.")
        @ParameterizedTest
        @CsvSource(value = {"1, 1000, 0", "1000, 1000, 1", "3333, 1000, 3"})
        void should_returnCount_when_givenAmount(long moneyAmount, long priceAmount, long count) {
            Money money = Money.valueOf(moneyAmount);
            Money price = Money.valueOf(priceAmount);

            long lottoCount = money.getPurchasableCount(price);

            Assertions.assertThat(lottoCount).isEqualTo(count);
        }
    }
}
