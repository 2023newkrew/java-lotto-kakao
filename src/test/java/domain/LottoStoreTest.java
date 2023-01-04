package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoStoreTest {
    private Integer LOTTO_COST = 1000;

    @ValueSource(ints = {10000, 2000, 4000})
    @ParameterizedTest
    void 구매금액만큼_로또를_구매한다(int money) {
        // given
        LottoStore lottoStore = new LottoStore();

        // when
        List<Lotto> lottos = lottoStore.purchase(money);

        // then
        assertThat(lottos.size()).isEqualTo(money/LOTTO_COST);
    }

    @ValueSource(ints = {500, 0, -1, -100, -2000})
    @ParameterizedTest
    void 구매금액이_부족할_경우_예외가_발생한다(int money) {
        // given
        LottoStore lottoStore = new LottoStore();

        // when, then
        assertThatThrownBy(() -> lottoStore.purchase(money))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
