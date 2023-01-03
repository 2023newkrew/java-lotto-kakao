package lotto.model.generator;

import lotto.model.lotto.Lotto;
import lotto.model.generator.LottosGenerator;
import lotto.model.generator.RandomLottosGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

class RandomLottosGeneratorTest {

    @DisplayName("로또를 0개 미만으로 생성할 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(longs = {-1L, 0L})
    void generateWithInvalidCount(long count) {
        LottosGenerator lottosGenerator = new RandomLottosGenerator();

        Assertions.assertThatThrownBy(() -> lottosGenerator.generate(count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("생성하려는 로또는 1개 이상이어야 합니다.");
    }

    @DisplayName("로또를 원하는 갯수만큼 생성한다.")
    @Test
    void generate() {
        LottosGenerator lottosGenerator = new RandomLottosGenerator();

        List<Lotto> lottos = lottosGenerator.generate(5);

        Assertions.assertThat(lottos).hasSize(5);
    }
}