package lotto.model;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LottoValueTest {
    @Test
    void 값이_1_45_사이에_있으면_객체가_생성되어야함(){
        assertThat(new LottoValue(6)).isEqualTo(new LottoValue(6));
    }

    @Test
    void 숫자가_1_45_사이에_있지_않으면_예외_발생() {
        assertThatCode(() -> new LottoValue(60))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 숫자가_음수이면_예외_발생() {
        assertThatCode(() -> new LottoValue(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

