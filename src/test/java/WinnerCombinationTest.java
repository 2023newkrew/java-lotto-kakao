import domain.LottoBall;
import domain.LottoResult;
import domain.LottoTicket;
import domain.WinnerCombination;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WinnerCombinationTest {

    LottoTicket winnerTicket;

    @Before
    public void setup() {
        winnerTicket = new LottoTicket(
                List.of(
                        new LottoBall(1),
                        new LottoBall(2),
                        new LottoBall(3),
                        new LottoBall(4),
                        new LottoBall(5),
                        new LottoBall(6)
                )
        );
    }

    @Test
    public void 당첨번호를_생성한다() {
        //given
        LottoBall bonusBall = new LottoBall(7);

        //when & then
        assertDoesNotThrow(() -> new WinnerCombination(winnerTicket, bonusBall));
    }

    @Test
    public void 당첨번호를_생성한다_보너스볼_중복() {
        //given
        LottoBall bonusBall = new LottoBall(6);

        //when & then
        assertThatThrownBy(() -> new WinnerCombination(winnerTicket, bonusBall))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스볼은 당첨번호와 중복될 수 없습니다.");
    }

    @Test
    public void 당첨_통계를_확인할_수_있다_1등() {
        //given
        LottoBall bonusBall = new LottoBall(7);
        WinnerCombination winnerCombination = new WinnerCombination(winnerTicket, bonusBall);

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
        assertThat(lottoResult).isEqualTo(LottoResult.SIX_MATCH);
    }

    @Test
    public void 당첨_통계를_확인할_수_있다_2등() {
        //given
        LottoBall bonusBall = new LottoBall(7);
        WinnerCombination winnerCombination = new WinnerCombination(winnerTicket, bonusBall);

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
        assertThat(lottoResult).isEqualTo(LottoResult.FIVE_MATCH_WITH_BONUS);
    }

    @Test
    public void 당첨_통계를_확인할_수_있다_3등() {
        //given
        LottoBall bonusBall = new LottoBall(7);
        WinnerCombination winnerCombination = new WinnerCombination(winnerTicket, bonusBall);

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
        assertThat(lottoResult).isEqualTo(LottoResult.FIVE_MATCH);
    }

    @Test
    public void 당첨_통계를_확인할_수_있다_4등() {
        //given
        LottoBall bonusBall = new LottoBall(7);
        WinnerCombination winnerCombination = new WinnerCombination(winnerTicket, bonusBall);

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
        assertThat(lottoResult).isEqualTo(LottoResult.FOUR_MATCH);
    }

    @Test
    public void 당첨_통계를_확인할_수_있다_5등() {
        //given
        LottoBall bonusBall = new LottoBall(7);
        WinnerCombination winnerCombination = new WinnerCombination(winnerTicket, bonusBall);

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
        assertThat(lottoResult).isEqualTo(LottoResult.THREE_MATCH);
    }
}
