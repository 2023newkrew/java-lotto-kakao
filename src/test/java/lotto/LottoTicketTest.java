package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketTest {

    @DisplayName("로또 숫자가 6개 초과이면 예외가 발생한다.")
    @Test
    void _lotto_6ball_restriction_7() {
        Assertions.assertThatThrownBy(() -> {
            new LottoTicket(new ArrayList<>(List.of(
                    new LottoBall(1),
                    new LottoBall(2),
                    new LottoBall(3),
                    new LottoBall(4),
                    new LottoBall(5),
                    new LottoBall(6),
                    new LottoBall(7)
            )));
        });
    }

    @DisplayName("로또 숫자가 6개 미만이면 예외가 발생한다.")
    @Test
    void _lotto_6ball_restriction_1() {
        Assertions.assertThatThrownBy(() -> {
            new LottoTicket(new ArrayList<>(List.of(
                    new LottoBall(1)
            )));
        });
    }

    @DisplayName("로또 숫자가 없으면 예외가 발생한다.")
    @Test
    void _lotto_6ball_restriction_0() {
        Assertions.assertThatThrownBy(() -> {
            new LottoTicket(new ArrayList<>());
        });
    }

    @DisplayName("로또 번호가 같은 경우")
    @Test
    void _lotto_equal() {
        List<LottoBall> lottoNumbers = new ArrayList<>(List.of(
                new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(5),
                new LottoBall(6)
        ));

        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        assertThat(lottoTicket).isEqualTo(new LottoTicket(lottoNumbers));
    }

    @DisplayName("로또 번호가 다른 경우")
    @Test
    void _lotto_not_equal() {
        List<LottoBall> lottoNumbers = new ArrayList<>(List.of(
                new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(5),
                new LottoBall(6)
        ));
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        assertThat(lottoTicket).isNotEqualTo(new LottoTicket(
                new ArrayList<>(List.of(
                        new LottoBall(1),
                        new LottoBall(2),
                        new LottoBall(3),
                        new LottoBall(4),
                        new LottoBall(5),
                        new LottoBall(7)
                ))
        ));
    }

    @DisplayName("로또 숫자는 중복될 수 없다.")
    @Test
    void _lotto_ball_duplication() {
        Assertions.assertThatThrownBy(() -> {
            new LottoTicket(new ArrayList<>(List.of(
                    new LottoBall(1),
                    new LottoBall(2),
                    new LottoBall(3),
                    new LottoBall(4),
                    new LottoBall(5),
                    new LottoBall(1)
            )));
        });
    }
}
