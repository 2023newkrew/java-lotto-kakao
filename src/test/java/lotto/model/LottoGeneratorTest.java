package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class LottoGeneratorTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    @DisplayName("로또를 생성한다.")
    void createLottoTest(int count) {
        LottoGenerator lottoGenerator = new LottoGenerator();
        lottoGenerator.createLottos(count);

        assertThat(lottoGenerator.getLottos().size()).isEqualTo(count);
    }
}
