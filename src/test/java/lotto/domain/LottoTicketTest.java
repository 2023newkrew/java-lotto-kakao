package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {

    @DisplayName("로또 숫자가 6개 초과이면 예외가 발생한다.")
    @Test
    void _lotto_6ball_restriction_7() {
        assertThatThrownBy(() -> new LottoTicket(1, 2, 3, 4, 5, 6, 7))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자는 6개여야 합니다.");
    }

    @DisplayName("로또 숫자가 6개 미만이면 예외가 발생한다.")
    @Test
    void _lotto_6ball_restriction_1() {
        assertThatThrownBy(() -> new LottoTicket(1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자는 6개여야 합니다.");
    }

    @DisplayName("로또 숫자가 없으면 예외가 발생한다.")
    @Test
    void _lotto_6ball_restriction_0() {
        assertThatThrownBy(() -> new LottoTicket())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자는 6개여야 합니다.");
    }

    @DisplayName("로또 숫자는 중복될 수 없다.")
    @Test
    void _lotto_ball_duplication() {
        assertThatThrownBy(() -> new LottoTicket(1, 2, 3, 4, 5, 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("로또 당첨 번호가 주어졌을 때 일치하는 개수를 계산한다.")
    void countMatchingNumber() {
        LottoTicket lottoTicket = new LottoTicket(1, 2, 3, 4, 5, 6);
        LottoTicket targetLotto = new LottoTicket(1, 2, 3, 4, 7, 8);

        assertThat(lottoTicket.countMatchingNumber(targetLotto)).isEqualTo(4);
    }
}
