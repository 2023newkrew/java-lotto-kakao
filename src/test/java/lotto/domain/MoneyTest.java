package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1000, -2000, -3000, 999})
    void 구매금액이_1000원_미만이면_예외가_발생한다(int money) {
        assertThatThrownBy(() -> new Money(money)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource({"14000,14", "2000,2", "1000,1", "1100,1"})
    void 구매금액만큼_살_수_있는_로또의_개수를_반환한다(int inputMoney, int amount) {
        Money money = new Money(inputMoney);
        assertThat(money.getLottoAmount()).isEqualTo(amount);
    }
}
