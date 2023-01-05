package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static lotto.constant.ExceptionMessages.INVALID_MONEY_LEFT;
import static lotto.domain.LottoStore.LOTTO_PRICE;
import static org.assertj.core.api.Assertions.*;

public class LottoStoreTest {
    @Test
    void 빈_로또를_가진_로또상점을_생성할_수_있다() {
        LottoStore lottoStore = new LottoStore(0);
        assertThat(lottoStore.getLottoAmount()).isEqualTo(0);
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 5000, 10000, 20000, 100000})
    void 남은_돈으로_로또를_자동_구매해준다(int money) {
        LottoStore lottoStore = new LottoStore(money);
        lottoStore.buyRandomLottosByMoneyLeft();
        assertThat(lottoStore.getLottoAmount()).isEqualTo(money / LOTTO_PRICE);
    }

    @Test
    void 수동으로_숫자를_적어_로또를_구매할_수_있다() {
        LottoStore lottoStore = new LottoStore(3000);
        lottoStore.buyLottosByNumbers(List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(3, 6, 9, 12, 15, 18),
                List.of(2, 4, 6, 8, 10, 12)
        ));
        assertThat(lottoStore.getLottoAmount()).isEqualTo(3);
    }

    @Test
    void 돈이_부족하면_예외가_발생한다() {
        LottoStore lottoStore = new LottoStore(2999);
        assertThatThrownBy(() -> lottoStore.buyLottosByNumbers(List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(3, 6, 9, 12, 15, 18),
                List.of(2, 4, 6, 8, 10, 12)
        ))).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_MONEY_LEFT);
    }
}
