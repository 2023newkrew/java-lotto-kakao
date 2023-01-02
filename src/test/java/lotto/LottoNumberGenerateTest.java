package lotto;

import lotto.util.Generator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumberGenerateTest {
    @Test
    @DisplayName("로또는 6개의 숫자로 이루어져 있다.")
    void lottoHas6Numbers() {
        Generator generator = () -> Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(generator.getNumbers()).hasSize(6);
    }

    @Test
    @DisplayName("로또 번호는 서로 다른 숫자다.")
    void lottoNumberNotDuplicate() {
        Generator generator = () -> Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(generator.getNumbers().stream().distinct().count()).isEqualTo(6);
    }

    @Test
    @DisplayName("로또 번호는 1과 45 사이의 숫자로만 이루어져 있다.")
    void lottoNumberin1To45() {
        Generator generator = () -> Arrays.asList(1, 5, 15 ,28, 39, 45);
        assertThat(generator.getNumbers().stream()
                .filter(number -> 1 <= number && number <= 45)
                .count()
        ).isEqualTo(6);
    }
}
