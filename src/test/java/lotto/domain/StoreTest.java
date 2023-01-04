package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StoreTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 20, 300, 550, 999})
    void 살_수_있는_로또가_없으면_예외를_반환한다(int inputMoney) {
        Money money = new Money(inputMoney);
        assertThatThrownBy(() -> new Store(money)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {14000, 2000, 1000, 1100})
    void 구매금액만큼_살_수_있는_로또의_개수를_반환한다(int inputMoney) {
        Money money = new Money(inputMoney);
        Store store = new Store(money);
        assertThat(store.getLottoAmount()).isEqualTo(inputMoney / Store.LOTTO_PRICE);
    }
}
