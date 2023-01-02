package lottoTest.model;

import lotto.model.LottoResult;
import lotto.model.LottoTicket;
import lotto.model.LottoTickets;
import lotto.model.LottoWinningNumber;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {
    @ParameterizedTest
    @MethodSource("getLottoResultTestGenerator")
    public void getLottoResultTest(LottoWinningNumber lottoWinningNumber, LottoResult expected) {
        //given
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.addLottoTicket(new LottoTicket(List.of(1,2,3,4,5,6)));
        lottoTickets.addLottoTicket(new LottoTicket(List.of(1,2,3,4,5,7)));
        lottoTickets.addLottoTicket(new LottoTicket(List.of(1,2,3,4,7,8)));

        //when & then
        assertThat(lottoTickets.getLottoResult(lottoWinningNumber)).isEqualTo(expected);
    }
    private static Stream<Arguments> getLottoResultTestGenerator(){
        return Stream.of(
                Arguments.of(new LottoWinningNumber(new LottoTicket(List.of(1,2,3,4,5,6)), 7), new LottoResult(List.of(1,1,0,1,0,0))),
                Arguments.of(new LottoWinningNumber(new LottoTicket(List.of(1,2,3,4,5,8)), 6), new LottoResult(List.of(0,1,2,0,0,0))),
                Arguments.of(new LottoWinningNumber(new LottoTicket(List.of(1,2,8,9,10,11)), 7), new LottoResult(List.of(0,0,0,0,1,2)))
        );
    }
}
