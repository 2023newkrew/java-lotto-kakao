package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMatchResultTest {
    @DisplayName("로또 번호가 6개 일치하고, 보너스 번호도 일치할 수 없다.")
    @Test
    void test1(){
        Assertions.assertThatThrownBy(() -> new LottoMatchResult(6, true))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 음수개 일치할 수 없다.")
    @Test
    void test2(){
        Assertions.assertThatThrownBy(() -> new LottoMatchResult(-1, true))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> new LottoMatchResult(-1, true))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
