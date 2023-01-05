package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.domain.WinnerCombinationFixture.createWinnerCombination;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class WinnerCombinationTest {

    @DisplayName("당첨 번호를 생성한다")
    @Test
    void create() {
        //given
        LottoTicket winnerTicket = new LottoTicket(
                List.of(
                        new LottoBall(1),
                        new LottoBall(2),
                        new LottoBall(3),
                        new LottoBall(4),
                        new LottoBall(5),
                        new LottoBall(6)
                )
        );
        LottoBall bonusBall = new LottoBall(7);

        //when & then
        assertDoesNotThrow(() -> new WinnerCombination(winnerTicket, bonusBall));
    }

    @DisplayName("당첨 번호를 생성할 때, 보너스 볼은 로또 티켓과 겹쳐서는 안된다")
    @Test
    void duplicateBonusBall() {
        //given
        LottoTicket winnerTicket = new LottoTicket(
                List.of(
                        new LottoBall(1),
                        new LottoBall(2),
                        new LottoBall(3),
                        new LottoBall(4),
                        new LottoBall(5),
                        new LottoBall(6)
                )
        );
        LottoBall bonusBall = new LottoBall(6);

        //when & then
        assertThatThrownBy(() -> new WinnerCombination(winnerTicket, bonusBall))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스볼은 당첨번호와 중복될 수 없습니다.");
    }

    @DisplayName("1등 당첨을 확인할 수 있다.")
    @Test
    void checkFirstPlace() {
        //given
        WinnerCombination winnerCombination = createWinnerCombination(1, 6, 7);

        //when
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

        LottoResult lottoResult = winnerCombination.compare(lottoTicket);

        // then
        assertThat(lottoResult).isEqualTo(LottoResult.FIRST_PLACE);
    }

    @DisplayName("2등 당첨을 확인할 수 있다.")
    @Test
    void checkSecondPlace() {
        //given
        WinnerCombination winnerCombination = createWinnerCombination(1, 6, 7);

        //when
        LottoTicket lottoTicket = new LottoTicket(
                List.of(
                        new LottoBall(1),
                        new LottoBall(2),
                        new LottoBall(3),
                        new LottoBall(4),
                        new LottoBall(5),
                        new LottoBall(7)
                )
        );

        LottoResult lottoResult = winnerCombination.compare(lottoTicket);

        // then
        assertThat(lottoResult).isEqualTo(LottoResult.SECOND_PLACE);
    }

    @DisplayName("3등 당첨을 확인할 수 있다.")
    @Test
    void checkThirdPlace() {
        //given
        WinnerCombination winnerCombination = createWinnerCombination(1, 6, 7);

        //when
        LottoTicket lottoTicket = new LottoTicket(
                List.of(
                        new LottoBall(1),
                        new LottoBall(2),
                        new LottoBall(3),
                        new LottoBall(4),
                        new LottoBall(5),
                        new LottoBall(8)
                )
        );

        LottoResult lottoResult = winnerCombination.compare(lottoTicket);

        // then
        assertThat(lottoResult).isEqualTo(LottoResult.THIRD_PLACE);
    }

    @DisplayName("4등 당첨을 확인할 수 있다.")
    @Test
    void checkFourthPlace() {
        //given
        WinnerCombination winnerCombination = createWinnerCombination(1, 6, 7);

        //when
        LottoTicket lottoTicket = new LottoTicket(
                List.of(
                        new LottoBall(1),
                        new LottoBall(2),
                        new LottoBall(3),
                        new LottoBall(4),
                        new LottoBall(8),
                        new LottoBall(9)
                )
        );

        LottoResult lottoResult = winnerCombination.compare(lottoTicket);

        // then
        assertThat(lottoResult).isEqualTo(LottoResult.FOURTH_PLACE);
    }

    @DisplayName("5등 당첨을 확인할 수 있다.")
    @Test
    void checkFifthPlace() {
        //given
        WinnerCombination winnerCombination = createWinnerCombination(1, 6, 7);

        //when
        LottoTicket lottoTicket = new LottoTicket(
                List.of(
                        new LottoBall(1),
                        new LottoBall(2),
                        new LottoBall(3),
                        new LottoBall(8),
                        new LottoBall(9),
                        new LottoBall(10)
                )
        );

        LottoResult lottoResult = winnerCombination.compare(lottoTicket);

        // then
        assertThat(lottoResult).isEqualTo(LottoResult.FIFTH_PLACE);
    }
}
