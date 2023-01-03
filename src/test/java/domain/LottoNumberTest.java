package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @Test
    void 로또숫자는_1_이상_45_이하의_숫자이다(){
        // given, when
        LottoNumber lottoNumber = new LottoNumber(40);

        // then
        assertThat(lottoNumber).isInstanceOf(LottoNumber.class);
    }

    @Test
    void 로또숫자는_1_미만이_될_수_없다() {
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또숫자는_45_초과가_될_수_없다() {
        assertThatThrownBy(() -> new LottoNumber(50))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
