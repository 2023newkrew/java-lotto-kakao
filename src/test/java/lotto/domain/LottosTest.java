package lotto.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {


    @Test
    @DisplayName("로또는_수동_및_자동으로_생성이_가능하다")
    void 로또는_수동_및_자동으로_생성이_가능하다() {
        Assertions.assertThatCode(
                () -> Lottos.createLottos(List.of(LottoNumbers.makeLottoNumbers(List.of(1, 2, 3, 4, 5, 6))), 3,
                        new RandomGeneratePolicy())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("등수_판별이_가능하다")
    void 등수_판별이_가능하다() {
        Assertions.assertThat(
                        Lottos.createLottos(List.of(LottoNumbers.makeLottoNumbers(List.of(1, 2, 3, 4, 5, 6))), 0,
                                        new RandomGeneratePolicy())
                                .getRanks(LottoAnswer.makeLottoAnswer(List.of(1, 2, 3, 4, 5, 6), 7)).get(LottoRank.FIRST))
                .isEqualTo(1);
    }
}