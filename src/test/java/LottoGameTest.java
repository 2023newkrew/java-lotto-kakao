import domain.lotto.LottoGame;
import domain.lotto.WinningNumbers;
import domain.lotto.result.LottoResultType;
import domain.lotto.result.LottoResults;
import domain.lotto.ticket.LottoTicket;
import domain.lotto.ticket.LottoTickets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoGameTest {

    @Test
    @DisplayName("여러 장의 로또 용지의 당첨 결과를 반환하는 기능")
    void getManyLottoTicketsResult(){
        LottoTicket lottoTicket1 = new LottoTicket(List.of(1,2,3,4,5,6));  //1등
        LottoTicket lottoTicket2 = new LottoTicket(List.of(1,2,3,4,5,7));  //2등
        LottoTicket lottoTicket3 = new LottoTicket(List.of(1,2,3,4,5,8));  //3등

        WinningNumbers winningNumbers = new WinningNumbers(List.of(1,2,3,4,5,6),7);
        LottoTickets lottoTickets = new LottoTickets(List.of(lottoTicket1, lottoTicket2, lottoTicket3));

        LottoGame lottoGame = new LottoGame(lottoTickets, winningNumbers);

        LottoResults lottoResults = lottoGame.getLottoTicketsResult();

        assertThat(lottoResults.getResultCount(LottoResultType.FIFTH_PLACE)).isEqualTo(0);
        assertThat(lottoResults.getResultCount(LottoResultType.FOURTH_PLACE)).isEqualTo(0);
        assertThat(lottoResults.getResultCount(LottoResultType.THIRD_PLACE)).isEqualTo(1);
        assertThat(lottoResults.getResultCount(LottoResultType.SECOND_PLACE)).isEqualTo(1);
        assertThat(lottoResults.getResultCount(LottoResultType.FIRST_PLACE)).isEqualTo(1);
    }


}
