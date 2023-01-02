import domain.LottoBall;
import domain.LottoTicket;
import domain.WinnerCombination;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WinnerCombinationTest {
    @Test
    public void 당첨번호를_생성한다() {
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

    @Test
    public void 당첨번호를_생성한다_보너스볼_중복() {
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
}
