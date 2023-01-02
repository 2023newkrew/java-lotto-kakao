import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoStoreTest {

    @CsvSource({"2000,2", "3020,3","10432,10"})
    @ParameterizedTest
    void 구매금액만큼_로또를_구매한다(int money, int amount) {
        // given
        LottoStore lottoStore = new LottoStore();

        // when
        List<Lotto> lottos = lottoStore.buyLotto(money);

        // then
        assertThat(lottos.size()).isEqualTo(amount);
    }

    @ValueSource(ints = {100, 400, -100, -2000, 0})
    @ParameterizedTest
    void 구매금액은_1000원_이상이여야_한다(int money) {
        // given
        LottoStore lottoStore = new LottoStore();

        // then
        assertThatThrownBy(() -> lottoStore.buyLotto(money))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
