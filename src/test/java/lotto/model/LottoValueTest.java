package lotto.model;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LottoValueTest {
    @Test
    void 입력된_1_45_사이_값을_갖는_로또_숫자_객체_생성(){
        assertThat(new LottoValue(6)).isEqualTo(new LottoValue(6));
    }

    @Test
    void 입력된_숫자가_1_45_사이에_있지_않으면_예외_발생() {
        assertThatCode(() -> new LottoValue(60))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

