package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {

    @Test
    @DisplayName("로또는 6개의 숫자를 가진다.")
    void lottoContains6NumsersTest() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto.getNumbers().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("로또는 1~45 사이의 숫자를 가진다.")
    void lottoContains1To45NumberTest() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        for (Integer number : lotto.getNumbers()) {
            assertThat(number).isGreaterThan(0)
                    .isLessThan(46);
        }
    }
}