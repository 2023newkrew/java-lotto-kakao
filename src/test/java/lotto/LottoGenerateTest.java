package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoPayment;
import lotto.util.ManualLottoGenerator;
import lotto.util.RandomLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoGenerateTest {
    @Test
    @DisplayName("로또 하나당 1000원이다. 2000원에 두장 살 수 있다")
    void 로또한장에천원() {
        assertThat(new LottoPayment(2500).getAmount()).isEqualTo(2);
    }

    @Test
    @DisplayName("여러장의 로또를 만들어 낼 수 있다.")
    void 여러장의로또구매() {
        List<Lotto> lottos = RandomLottoGenerator.generateLottos(10);
        assertThat(lottos.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("지불 한도 내에서 구매할 개수를 선택할 수 있다.")
    void 지불한도내에서수동구매() {
        LottoPayment payment = new LottoPayment(10000);
        payment.buyLotto(5);
        assertThat(payment.getWallet()).isEqualTo(5000);
    }

    @Test
    @DisplayName("한도 초과시 에러를 던진다.")
    void 한도초과() {
        LottoPayment payment = new LottoPayment(1000);
        assertThatThrownBy(()->payment.buyLotto(2)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("수동 입력한 문자열로 로또를 생성한다.")
    void 수동테스트() {
        String userInput = "1,2,3,4,5,6";
        Lotto userManualLotto = ManualLottoGenerator.generateLotto(userInput);
        assertThat(userManualLotto).isEqualTo(new Lotto(1,2,3,4,5,6));
    }
}
