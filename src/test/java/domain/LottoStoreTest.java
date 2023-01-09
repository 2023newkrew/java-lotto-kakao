package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class LottoStoreTest {
    @ValueSource(ints = {10000, 2000, 4000, 4500, 1234})
    @ParameterizedTest
    void 구매금액으로_구매할_수_있는_로또_수를_구한다(int money) {
        // given
        LottoStore lottoStore = new LottoStore();

        // when
        Integer amount = lottoStore.getPurchaseAmount(money);

        // then
        assertThat(amount).isEqualTo(money / LottoStore.LOTTO_COST);
    }

    @CsvSource({"2,2123", "20,20000", "5,10000"})
    @ParameterizedTest
    void 구매금액으로_개수만큼_로또를_구매할_수_있는지_확인한다(int amount, int money) {
        // given
        LottoStore lottoStore = new LottoStore();

        // when, then
        assertThatNoException().isThrownBy(() -> lottoStore.validatePurchase(money, amount));
    }

    @ValueSource(ints = {500, 0, -1, -100, -2000})
    @ParameterizedTest
    void 구매금액이_로또_금액보다_작을_경우_예외가_발생한다(int money) {
        // given
        LottoStore lottoStore = new LottoStore();

        // when, then
        assertThatThrownBy(() -> lottoStore.validatePurchase(money, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @CsvSource({"2,1999", "20,4000", "5,4200"})
    @ParameterizedTest
    void 구매하고자_로또_수보다_구매금액이_부족할_경우_예외가_발생한다(int amount, int money) {
        // given
        LottoStore lottoStore = new LottoStore();

        // when, then
        assertThatThrownBy(() -> lottoStore.validatePurchase(money, amount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
