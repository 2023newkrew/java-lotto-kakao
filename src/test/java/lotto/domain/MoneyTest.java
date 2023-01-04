package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1000, -2000, -3000, 999})
    void 구매금액이_1000원_미만이면_예외가_발생한다(int money) {
        assertThatThrownBy(() -> new Money(money)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {14000, 2000, 1000, 1100})
    void 구매금액만큼_살_수_있는_로또의_개수를_반환한다(int inputMoney) {
        Money money = new Money(inputMoney);
        int expectedAmount = inputMoney / Money.LOTTO_PRICE;

        assertThat(money.getLottoAmount()).isEqualTo(expectedAmount);
    }
}
