package lotto.model;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ManualAndAdditionalLottosGeneratorTest {

    @DisplayName("수동으로 로또를 생성한다.")
    @Test
    void generateManualLottos() {
        List<Lotto> lottos = List.of(
                Lotto.create(1, 2, 3, 4, 5, 6),
                Lotto.create(11, 12, 13, 14, 15, 16),
                Lotto.create(21, 22, 23, 24, 25, 26),
                Lotto.create(31, 32, 33, 34, 35, 36)
        );
        ManualAndAdditionalLottosGenerator sut = new ManualAndAdditionalLottosGenerator(lottos, new RandomLottosGenerator());

        Assertions.assertThat(sut.generate(new Money(4000)))
                .containsExactlyInAnyOrderElementsOf(lottos);
    }

    @DisplayName("부족한 금액으로 수동 로또를 생성한다.")
    @Test
    void generateByDeficientMoney() {
        List<Lotto> lottos = List.of(
                Lotto.create(1, 2, 3, 4, 5, 6),
                Lotto.create(11, 12, 13, 14, 15, 16),
                Lotto.create(21, 22, 23, 24, 25, 26),
                Lotto.create(31, 32, 33, 34, 35, 36)
        );
        ManualAndAdditionalLottosGenerator sut = new ManualAndAdditionalLottosGenerator(lottos, new RandomLottosGenerator());

        Assertions.assertThatThrownBy(() -> sut.generate(new Money(3999)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액이 부족하여 수동으로 로또를 생성할 수 없습니다.");
    }

    @DisplayName("금액이 남을 경우 자동 로또를 생성한다.")
    @Test
    void generateBySufficientMoney() {
        List<Lotto> lottos = List.of(
                Lotto.create(1, 2, 3, 4, 5, 6),
                Lotto.create(11, 12, 13, 14, 15, 16),
                Lotto.create(21, 22, 23, 24, 25, 26),
                Lotto.create(31, 32, 33, 34, 35, 36)
        );
        ManualAndAdditionalLottosGenerator sut = new ManualAndAdditionalLottosGenerator(lottos, new RandomLottosGenerator());

        List<Lotto> actual = sut.generate(new Money(10_000));

        Assertions.assertThat(actual)
                .hasSize(10)
                .containsAnyElementsOf(lottos);
    }

}