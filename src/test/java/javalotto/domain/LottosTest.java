package javalotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class LottosTest {
    @Test
    void test() {
        Lotto lotto = Lotto.from(List.of(1,2,3,4,5,6));
        Lottos lottos = Lottos.from(List.of(lotto));

        assertThat(lottos.getLottos()).hasSize(1);

        lottos.getLottos().remove(0);
        assertThat(lottos.getLottos()).hasSize(1);
    }
}
