package lotto.models;

import static lotto.common.LottoConfiguration.LOTTO_COUNT;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoGeneratorTest {

    private final LottoGenerator lottoGenerator = new LottoGenerator();

    @Test
    @DisplayName("자동으로 로또를 생성할 수 있다.")
    public void testCreateLotto() {
        List<Integer> lotto = lottoGenerator.createLotto().getNumbers();
        assertThat(lotto).hasSize(6);
        assertThat(lotto).isSorted();
        for (Integer number : lotto) {
            assertThat(number).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(45);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000})
    @DisplayName("자동으로 다수의 로또를 생성할 수 있다.")
    public void testCreateLottos(Integer input) {
        List<Lotto> lottos = lottoGenerator.createLottos(input);
        assertThat(lottos).hasSize(input);
        lottos.forEach((lotto -> assertThat(lotto.getNumbers()).hasSize(LOTTO_COUNT)));
    }
}