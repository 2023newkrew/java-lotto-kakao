package lottoTest.model;

import lotto.exception.ErrorCode;
import lotto.exception.LottoException;
import lotto.model.LottoTicket;
import lotto.model.LottoWinningNumber;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LottoWinningNumberTest {
    @Test
    public void correctBonusBall() {
        //given
        List<Integer> input = List.of(1,2,3,4,5,6);
        LottoTicket lottoTicket = new LottoTicket(input);

        //when & then
        assertDoesNotThrow(() -> new LottoWinningNumber(lottoTicket, 7));
    }

    @Test
    public void wrongBonusBall() {
        //given
        List<Integer> input = List.of(1,2,3,4,5,6);
        LottoTicket lottoTicket = new LottoTicket(input);

        //when & then
        assertThatThrownBy(() -> new LottoWinningNumber(lottoTicket, 1))
                .isInstanceOf(LottoException.class)
                .hasMessage(ErrorCode.INVALID_BONUS_BALL.getMessage());
    }
}
