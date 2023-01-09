package dto;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoRank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {
    @Test
    void 보너스번호와_당첨번호는_중복될_수_없다() {
        // given
        Lotto lotto = Lotto.ofManual(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(1);

        // when, then
        assertThatThrownBy(() -> new WinningLotto((lotto), bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨로또는_로또의_당첨등수를_알_수_있다_1등() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = Lotto.ofManual(numbers);
        WinningLotto winningLotto = new WinningLotto(Lotto.ofManual(numbers), new LottoNumber(7));

        // when
        LottoRank place = winningLotto.getLottoRank(lotto);

        // then
        assertThat(place).isEqualTo(LottoRank.FIRST_RANK);
    }

    @Test
    void 당첨로또는_로또의_당첨등수를_알_수_있다_2등() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 11);
        Lotto lotto = Lotto.ofManual(List.of(1, 2, 3, 4, 5, 7));
        WinningLotto winningLotto = new WinningLotto(Lotto.ofManual(numbers), new LottoNumber(7));

        // when
        LottoRank place = winningLotto.getLottoRank(lotto);

        // then
        assertThat(place).isEqualTo(LottoRank.SECOND_RANK);
    }
}
