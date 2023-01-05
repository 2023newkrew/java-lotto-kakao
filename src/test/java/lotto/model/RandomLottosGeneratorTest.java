package lotto.model;

import lotto.model.Lotto;
import lotto.model.LottosGenerator;
import lotto.model.RandomLottosGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

class RandomLottosGeneratorTest {

    @DisplayName("로또를 원하는 갯수만큼 생성한다.")
    @Test
    void generate() {
        LottosGenerator lottosGenerator = new RandomLottosGenerator();

        List<Lotto> lottos = lottosGenerator.generate(new Money(5000));

        Assertions.assertThat(lottos).hasSize(5);
    }
}