package lotto.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoGeneratorTest {
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 10000, 10000, 30000, 33300})
    void 입력된_수의_로또를_자동_생성해_반환한다(int number) {
        List<Lotto> lottos = LottoGenerator.generate(number);

        assertThat(lottos).hasSize(number/1000);
    }
}
