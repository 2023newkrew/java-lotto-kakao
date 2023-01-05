package lottoTest.model;

import lotto.exception.ErrorCode;
import lotto.exception.LottoException;
import lotto.model.LottoNumber;
import lotto.model.LottoTicket;
import lotto.model.LottoWinningNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LottoWinningNumberTest {
    private final List<LottoNumber> input = createLottoNumberList(List.of(1,2,3,4,5,6));

    @Test
    @DisplayName("보너스 번호가 winningNumber에 포함되어 있지 않은 경우")
    public void correctBonusBall() {
        //given
        LottoTicket lottoTicket = new LottoTicket(input);

        //when & then
        assertDoesNotThrow(() -> new LottoWinningNumber(lottoTicket, new LottoNumber(7)));
    }

    @Test
    @DisplayName("보너스 번호가 winningNumber에 포함되어 있는 경우")
    public void wrongBonusBall() {
        //given
        LottoTicket lottoTicket = new LottoTicket(input);

        //when & then
        assertThatThrownBy(() -> new LottoWinningNumber(lottoTicket, new LottoNumber(1)))
                .isInstanceOf(LottoException.class)
                .hasMessage(ErrorCode.INVALID_BONUS_BALL.getMessage());
    }

    private static List<LottoNumber> createLottoNumberList(List<Integer> integerList){
        return integerList.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
