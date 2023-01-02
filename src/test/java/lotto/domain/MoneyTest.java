package lotto.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 3000, 4000})
    void 구매금액은_1000원_단위여야_한다(int money) {
        assertThatCode(() -> new Money(money)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {1111, 21, 2001})
    void 구매금액이_1000원_단위가_아니면_예외가_발생한다(int money) {
        assertThatThrownBy(() -> new Money(money)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1000, -2000, -3000})
    void 구매금액이_양수가_아니면_예외가_발생한다(int money) {
        assertThatThrownBy(() -> new Money(money)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource({"14000,14", "2000,2", "1000,1"})
    void 구매금액만큼_살_수_있는_로또의_개수를_반환한다(int inputMoney, int amount) {
        Money money = new Money(inputMoney);
        assertThat(money.getLottoAmount()).isEqualTo(amount);
    }
}
