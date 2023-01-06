package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoCountTest {
    @Test
    void createLottoCountTest(){
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new LottoCount(10, 11));

        Assertions.assertThatCode(()->new LottoCount(10, 2))
                .doesNotThrowAnyException();
    }
}
