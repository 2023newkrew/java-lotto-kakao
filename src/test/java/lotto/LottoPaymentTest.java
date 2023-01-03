package lotto;

import lotto.util.LottoPayment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LottoPaymentTest {
    @Test
    @DisplayName("로또 하나당 1000원이다.")
    void 한장에천원() {
        assertThat(LottoPayment.getAmount(2500)).isEqualTo(2);
    }
}
