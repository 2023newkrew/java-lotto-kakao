package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class LottosTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    @DisplayName("로또를 생성한다.")
    void lottosTest(int count) {
        Lottos lottos = new Lottos(count);
        
        assertThat(lottos.getLottos().size()).isEqualTo(count);
    }

    @Test
    void name() {

    }
}
