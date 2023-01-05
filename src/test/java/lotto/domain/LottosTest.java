package lotto.domain;

import lotto.constant.LottoGrade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static lotto.constant.ExceptionMessages.*;
import static org.assertj.core.api.Assertions.*;

public class LottosTest {
    @Test
    void 빈_로또들을_생성할_수_있다() {
        Lottos lottos = new Lottos();
        assertThat(lottos.size()).isEqualTo(0);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 10, 100, 200})
    void 랜덤으로_입력된_숫자만큼의_로또들을_생성할_수_있다(int amount) {
        Lottos lottos = Lottos.generateRandomLottosByAmounts(amount);
        assertThat(lottos.size()).isEqualTo(amount);
    }

    @Test
    void 숫자_리스트로_로또들을_생성할_수_있다() {
        Lottos lottos = Lottos.generateLottosByNumbers(List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(3, 6, 9, 12, 15, 18),
                List.of(2, 4, 6, 8, 10, 12)
        ));
        assertThat(lottos.size()).isEqualTo(3);
    }

    @Test
    void 범위를_벗어난_숫자에선_예외가_발생한다() {
        assertThatThrownBy(() -> Lottos.generateLottosByNumbers(List.of(
                List.of(1, 2, 3, 4, 5, 46)
        )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_NUMBER_RANGE);
    }

    @Test
    void 중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> Lottos.generateLottosByNumbers(List.of(
                List.of(1, 2, 3, 4, 5, 1)
        )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DUPLICATED_LOTTO_NUMBER);
    }

    @Test
    void 로또들을_합칠_수_있다() {
        Lottos lottos = Lottos.generateRandomLottosByAmounts(10);
        lottos = lottos.addAll(Lottos.generateRandomLottosByAmounts(10));

        assertThat(lottos.size()).isEqualTo(20);
    }

    @Test
    void 당첨결과를_반환할_수_있다() {
        Lottos lottos = Lottos.generateLottosByNumbers(List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(3, 6, 9, 12, 15, 18),
                List.of(2, 4, 6, 8, 10, 12)
        ));
        WinningLotto winningLotto = new WinningLotto(List.of(2, 3, 4, 5, 6, 7), 10);

        assertThat(lottos.getResult(winningLotto))
                .containsEntry(LottoGrade.FIFTH, 1)
                .containsEntry(LottoGrade.THIRD, 1)
                .containsEntry(LottoGrade.NONE, 1);
    }
}
