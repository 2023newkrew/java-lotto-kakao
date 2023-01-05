package domain;

import dto.PurchasedLotto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoStoreTest {
    private Integer LOTTO_COST = 1000;

    @ValueSource(ints = {10000, 2000, 4000})
    @ParameterizedTest
    void 로또를_구매금액만큼_자동으로_구매한다(int money) {
        // given
        LottoStore lottoStore = new LottoStore();

        // when
        PurchasedLotto purchase = lottoStore.purchase(money, Collections.emptyList());

        // then
        assertThat(purchase.getAuto().size()).isEqualTo(money / LOTTO_COST);
    }

    @ValueSource(ints = {500, 0, -1, -100, -2000})
    @ParameterizedTest
    void 로또를_하나도_구매하지_못했을_경우_예외가_발생한다(int money) {
        // given
        LottoStore lottoStore = new LottoStore();

        // when, then
        assertThatThrownBy(() -> lottoStore.purchase(money, Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또를_수동으로_구매한다() {
        // given
        int money = 2300;
        List<Lotto> manualLottos = List.of(
                Lotto.ofManual(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.ofManual(List.of(7, 8, 9, 10, 11, 12))
                );
        LottoStore lottoStore = new LottoStore();

        // when
        PurchasedLotto purchase = lottoStore.purchase(money, manualLottos);

        // then
        assertThat(purchase.getManual()).containsExactlyElementsOf(manualLottos);
    }

    @Test
    void 로또를_수동으로_구매_후_구매금액이_남으면_해당_금액만큼_자동으로_구매된다() {
        // given
        int money = 5000;
        List<Lotto> manualLottos = List.of(
                Lotto.ofManual(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.ofManual(List.of(7, 8, 9, 10, 11, 12))
                );
        LottoStore lottoStore = new LottoStore();

        // when
        PurchasedLotto purchase = lottoStore.purchase(money, manualLottos);

        // then
        assertThat(purchase.getAuto().size()).isEqualTo(3);
        assertThat(purchase.getManual().size()).isEqualTo(2);
        assertThat(purchase.getManual()).containsExactlyElementsOf(manualLottos);
    }

    @Test
    void 구매하고자_하는_수동_로또보다_구매금액이_부족할_경우_예외가_발생한다() {
        // given
        int money = 1999;
        List<Lotto> manualLottos = List.of(
                Lotto.ofManual(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.ofManual(List.of(7, 8, 9, 10, 11, 12))
        );
        LottoStore lottoStore = new LottoStore();

        // when, then
        assertThatThrownBy(() -> lottoStore.purchase(money, manualLottos))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
