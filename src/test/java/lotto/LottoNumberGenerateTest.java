package lotto;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {

    @Test
    @DisplayName("로또는 6개의 숫자로 이루어져 있다.")
    void lottoHas6Numbers() {
        Lotto lotto = new Lotto();
        assertThat(lotto.getNumbers().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("로또 번호는 서로 다른 숫자다.")
    void lottoNumberNotDuplicate() {

    }
}
