package lotto.domain;

import java.util.List;

import org.junit.jupiter.api.Test;

import static lotto.constant.ExceptionMessages.INVALID_DUPLICATED_LOTTO_NUMBER;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LottoTest {
    @Test
    void 리스트를_넣어서_로또를_생성할_수_있다() {
        assertDoesNotThrow(() -> Lotto.generateLotto(List.of(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void 숫자_리스트_크기가_6개가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> Lotto.generateLotto(List.of(1, 2, 3)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또에_저장되는_숫자는_LottoNumber_객체이다() {
        Lotto lotto = Lotto.generateLotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto.getNumbers().toArray()[0]).isInstanceOf(LottoNumber.class);
    }

    @Test
    void 로또에_중복된_숫자가_저장될_수_없다() {
        assertThatThrownBy(() -> Lotto.generateLotto(List.of(1, 2, 3, 4, 5, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DUPLICATED_LOTTO_NUMBER);
    }

    @Test
    void 로또를_자동_생성해_반환한다() {
        Lotto lotto = Lotto.generateRandomLotto();

        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @Test
    void 수동으로_로또를_생성할_수_있다() {
        Lotto lotto = Lotto.generateLotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }
}
