package lotto.domain;

import static lotto.constant.MessageConstant.INVALID_DUPLICATED_LOTTO_NUMBER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @Test
    void 당첨로또를_생성할_수_있다() {
        assertDoesNotThrow(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7));
    }

    @Test
    void 보너스_숫자는_로또_숫자와_중복될_수_없다() {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DUPLICATED_LOTTO_NUMBER);
    }
}
