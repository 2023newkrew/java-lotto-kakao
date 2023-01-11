package lotto.model;

import lotto.model.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


public class MoneyTest {

    @DisplayName("money가 0원 이하일 때 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(longs = {0L, -1L, -100L})
    void createMoney(long amount) {

        Assertions.assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 0원을 초과해야 합니다.");
    }

    @DisplayName("1000원 당 1개의 로또를 발급한다.")
    @ParameterizedTest
    @CsvSource(value = {"1, 0", "1000, 1", "3333, 3"})
    void getLottoCount(long amount, long expected) {
        Money money = new Money(amount);

        long lottoCount = money.getLottoCount();

        Assertions.assertThat(lottoCount).isEqualTo(expected);
    }
}
