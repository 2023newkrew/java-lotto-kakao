package lotto.domain;

import java.util.List;
import lotto.config.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {


    @Test
    @DisplayName("로또는_수동_및_자동으로_생성이_가능하다")
    void 로또는_수동_및_자동으로_생성이_가능하다() {
        Assertions.assertThatCode(
                        () -> Lottos.createLottos(List.of(LottoNumbers.createLottoNumbers(List.of(1, 2, 3, 4, 5, 6))), 3 ))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("등수_판별이_가능하다")
    void 등수_판별이_가능하다() {
        Assertions.assertThat(
                        Lottos.createLottos(List.of(LottoNumbers.createLottoNumbers(List.of(1, 2, 3, 4, 5, 6))), 0)
                                .getRanks(LottoAnswer.createLottoAnswer(List.of(1, 2, 3, 4, 5, 6), 7)).get(LottoRank.FIRST))
                .isEqualTo(1);
    }
}