package javalotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {

    @Test
    void addAll() {
        Lottos lottos1 = Lottos.from(List.of(
                Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.from(List.of(7, 8, 9, 10, 11, 12)),
                Lotto.from(List.of(13, 14, 15, 16, 17, 18))
        ));
        Lottos lottos2 = Lottos.from(List.of(
                Lotto.from(List.of(19, 20, 21, 22, 23, 24)),
                Lotto.from(List.of(25, 26, 27, 28, 29, 30)),
                Lotto.from(List.of(31, 32, 33, 34, 35, 36))
        ));

        Lottos result = lottos1.addAll(lottos2);

        assertThat(result).hasToString("[1, 2, 3, 4, 5, 6]\n" +
                "[7, 8, 9, 10, 11, 12]\n" +
                "[13, 14, 15, 16, 17, 18]\n" +
                "[19, 20, 21, 22, 23, 24]\n" +
                "[25, 26, 27, 28, 29, 30]\n" +
                "[31, 32, 33, 34, 35, 36]");
    }
}