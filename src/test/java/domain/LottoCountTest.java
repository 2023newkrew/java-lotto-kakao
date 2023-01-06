package domain;

import org.junit.jupiter.api.Test;
import utils.LottoCalculator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoCountTest {

    @Test
    void 수동으로_구매할_로또_수가_구입금액을_넘으면_예외가_발생한다() {
        // given
        Payment payment = new Payment(14000);
        int manualLottoCount = LottoCalculator.calculateLottoCount(payment) + 1;

        // when, then
        assertThatThrownBy(() -> LottoCount.of(payment, manualLottoCount))
                .isInstanceOf(RuntimeException.class);
    }

}