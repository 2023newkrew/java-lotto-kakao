package domain;

import dto.WinningLotto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @Test
    void 당첨번호는_숫자_6개이어야_한다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7);

        assertThatThrownBy(()-> Lotto.ofNumbers(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호는_중복_불가하다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 6, 6);

        assertThatThrownBy(()-> Lotto.ofNumbers(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또는_자신의_당첨등수를_알_수_있다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        Lotto lotto = Lotto.ofNumbers(numbers);
        WinningLotto winningLotto = new WinningLotto(Lotto.ofNumbers(numbers), new LottoNumber(7));
        Rank place = lotto.getRank(winningLotto);

        assertThat(place).isEqualTo(Rank.FIRST_RANK);
    }

    @Test
    void 로또는_자신의_당첨등수를_알_수_있다_2등() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 7);

        Lotto lotto = Lotto.ofNumbers(numbers);
        WinningLotto winningLotto = new WinningLotto(Lotto.ofNumbers(numbers), new LottoNumber(7));
        Rank place = lotto.getRank(winningLotto);

        assertThat(place).isEqualTo(Rank.SECOND_RANK);
    }
}
