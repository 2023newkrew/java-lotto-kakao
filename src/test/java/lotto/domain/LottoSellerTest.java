package lotto.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoSellerTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1000, -2000, -3000, 999})
    void 구매금액이_1000원_미만이면_예외가_발생한다(int inputMoney) {
        assertThatThrownBy(() -> LottoSeller.getLottoAmount(new Money(inputMoney)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource({"14000,14", "2000,2", "1000,1", "1100,1"})
    void 구매금액만큼_살_수_있는_로또의_개수를_반환한다(int inputMoney, int amount) {
        Money money = new Money(inputMoney);
        assertThat(LottoSeller.getLottoAmount(money)).isEqualTo(amount);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 7, 9})
    void 랜덤으로_구매할_장수가_입력되면_장수만큼_로또_번호들을_반환한다(int amount) {
        List<LottoNumbers> lottos = LottoSeller.generateRandomLottos(amount);
        assertThat(lottos.size()).isEqualTo(amount);
    }
}
