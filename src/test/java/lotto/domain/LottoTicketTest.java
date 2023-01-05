package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LottoTicketTest {

    @DisplayName("하나의 로또티켓은 6개의 로또번호를 갖는다")
    @Test
    void createLottoTicket() {
        //given
        List<LottoBall> lottoBalls = List.of(
                new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(5),
                new LottoBall(6)
        );

        // when & then
        assertDoesNotThrow(() -> new LottoTicket(lottoBalls));
    }

    @DisplayName("하나의 로또티켓은 6개의 로또번호를 가지며, 중복된 번호를 가지면 예외가 발생한다.")
    @Test
    void createLottoTicketWithDuplicate() {
        //given
        List<LottoBall> lottoBalls = List.of(
                new LottoBall(1),
                new LottoBall(1),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(5),
                new LottoBall(6)
        );

        // when & then
        assertThatThrownBy(() -> new LottoTicket(lottoBalls))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("하나의 로또티켓은 6개의 로또번호를 가지지 않는다면 예외가 발생한다.")
    @Test
    void createLottoTicketWithFiveBalls() {
        //given
        List<LottoBall> lottoBalls = List.of(
                new LottoBall(1),
                new LottoBall(2),
                new LottoBall(3),
                new LottoBall(4),
                new LottoBall(5)
        );

        // when & then
        assertThatThrownBy(() -> new LottoTicket(lottoBalls))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 티켓에서 해당 로또볼을 가지고 있는지 검사할 수 있다.")
    @Test
    void contains() {
        //given
        LottoTicket lottoTicket = new LottoTicket(
                List.of(
                        new LottoBall(1),
                        new LottoBall(2),
                        new LottoBall(3),
                        new LottoBall(4),
                        new LottoBall(5),
                        new LottoBall(6)
                )
        );

        // when
        boolean result = lottoTicket.contains(new LottoBall(1));

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("서로 다른 로또 티켓들이 몇 개를 공통으로 가지고 있는지 알 수 있다.")
    @Test
    void countMatch() {
        //given
        LottoTicket lottoTicket1 = new LottoTicket(
                List.of(
                        new LottoBall(1),
                        new LottoBall(2),
                        new LottoBall(3),
                        new LottoBall(4),
                        new LottoBall(5),
                        new LottoBall(6)
                )
        );

        LottoTicket lottoTicket2 = new LottoTicket(
                List.of(
                        new LottoBall(1),
                        new LottoBall(2),
                        new LottoBall(3),
                        new LottoBall(4),
                        new LottoBall(5),
                        new LottoBall(8)
                )
        );

        // when
        int matchCount = lottoTicket1.countMatch(lottoTicket2);

        // then
        assertThat(matchCount).isEqualTo(5);
    }

    @DisplayName("번호를 통해 로또 티켓을 만들 수 있다.")
    @Test
    void createFromNumbers() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // when
        LottoTicket lottoTicket = LottoTicket.fromNumbers(numbers);

        // then
        List<LottoBall> lottoBalls = lottoTicket.getLottoBalls();
        assertThat(lottoBalls.get(0)).isEqualTo(new LottoBall(1));
        assertThat(lottoBalls.get(1)).isEqualTo(new LottoBall(2));
        assertThat(lottoBalls.get(2)).isEqualTo(new LottoBall(3));
        assertThat(lottoBalls.get(3)).isEqualTo(new LottoBall(4));
        assertThat(lottoBalls.get(4)).isEqualTo(new LottoBall(5));
        assertThat(lottoBalls.get(5)).isEqualTo(new LottoBall(6));
    }
}
