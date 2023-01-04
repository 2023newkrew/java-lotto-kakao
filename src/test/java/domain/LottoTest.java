package domain;

import dto.WinningLotto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @Test
    void 로또번호는_숫자_6개이어야_한다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7);

        // when, then
        assertThatThrownBy(()-> Lotto.ofManual(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또번호는_중복_될_수_없다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 6, 6);

        // when, then
        assertThatThrownBy(()-> Lotto.ofManual(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또는_자신의_당첨등수를_알_수_있다_1등() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        Lotto lotto = Lotto.ofManual(numbers);
        WinningLotto winningLotto = new WinningLotto(Lotto.ofManual(numbers), new LottoNumber(7));

        // when
        LottoRank place = lotto.getRank(winningLotto);

        // then
        assertThat(place).isEqualTo(LottoRank.FIRST_RANK);
    }

    @Test
    void 로또는_자신의_당첨등수를_알_수_있다_2등() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 11);

        Lotto lotto = Lotto.ofManual(List.of(1, 2, 3, 4, 5, 7));
        WinningLotto winningLotto = new WinningLotto(Lotto.ofManual(numbers), new LottoNumber(7));

        // when
        LottoRank place = lotto.getRank(winningLotto);

        // then
        assertThat(place).isEqualTo(LottoRank.SECOND_RANK);
    }
}
