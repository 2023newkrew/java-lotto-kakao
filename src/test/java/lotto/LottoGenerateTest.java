package lotto;

import lotto.domain.Lotto;
import lotto.util.LottoPayment;
import lotto.util.RandomLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoGenerateTest {
    @Test
    @DisplayName("로또 하나당 1000원이다. 2000원에 두장 살 수 있다")
    void 로또한장에천원() {
        assertThat(LottoPayment.getAmount(2500)).isEqualTo(2);
    }

    @Test
    @DisplayName("여러장의 로또를 만들어 낼 수 있다.")
    void 여러장의로또구매() {
        List<Lotto> lottos = RandomLottoGenerator.generateLottos(10);
    }
}
