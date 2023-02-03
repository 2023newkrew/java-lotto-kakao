package lotto;

import lotto.config.LottoConfig;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class LottoNumberGenerateTest {
    @Test
    @DisplayName("로또는 6개의 숫자로 이루어져 있다.")
    void lottoHas6Numbers() {
        assertThatNoException().isThrownBy(() -> {
            new Lotto(1, 2, 3, 4, 5, 6);
        });

        assertThatThrownBy(() -> {
            new Lotto(1, 2, 3, 4, 5);
        }).isInstanceOf(RuntimeException.class);

        assertThatThrownBy(() -> {
            new Lotto(1, 2, 3, 4, 5, 6, 7);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("로또 번호는 서로 다른 숫자다.")
    void lottoNumberNotDuplicate() {
        assertThatThrownBy(() -> {
            new Lotto(1, 1, 3, 4, 5, 6);
        }).isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0, 1, 2, 5, 25, 44, 45, 46, 47, 100})
    @DisplayName("로또 번호는 1과 45 사이의 숫자로만 이루어져 있다.")
    void lottoNumberBetween1To45(int n) {
        if (LottoConfig.MIN_NUMBER <= n && n <= LottoConfig.MAX_NUMBER) {
            assertThatNoException().isThrownBy(() -> {
                new LottoNumber(n);
            });
            return;
        }
        assertThatThrownBy(() -> {
            new LottoNumber(n);
        }).isInstanceOf(RuntimeException.class);
    }
}
