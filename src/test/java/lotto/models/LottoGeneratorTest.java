package lotto.models;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {
    @Test
    @DisplayName("자동으로 로또를 생성할 수 있다.")
    public void testCreateLotto() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        List<Integer> lotto = lottoGenerator.createLotto();
        assertThat(lotto).hasSize(6);
        assertThat(lotto).isSorted();
        for (Integer number : lotto) {
            assertThat(number).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(45);
        }
    }
}