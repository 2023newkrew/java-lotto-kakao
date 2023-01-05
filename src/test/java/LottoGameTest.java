import domain.lotto.LottoGame;
import domain.lotto.WinningNumbers;
import domain.lotto.result.LottoResultType;
import domain.lotto.result.LottoResults;
import domain.lotto.ticket.LottoNumber;
import domain.lotto.ticket.LottoTicket;
import domain.lotto.ticket.LottoTicketList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoGameTest {

    @Test
    @DisplayName("여러 장의 로또 용지의 당첨 결과를 반환하는 기능")
    void getManyLottoTicketsResult() {
        LottoTicket lottoTicket1 = new LottoTicket(LottoNumber.numbersToLottoNumbers(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6))));
        LottoTicket lottoTicket2 = new LottoTicket(LottoNumber.numbersToLottoNumbers(new ArrayList<>(List.of(1, 2, 3, 4, 5, 7))));
        LottoTicket lottoTicket3 = new LottoTicket(LottoNumber.numbersToLottoNumbers(new ArrayList<>(List.of(1, 2, 3, 4, 5, 8))));

        WinningNumbers winningNumbers = new WinningNumbers(LottoNumber.numbersToLottoNumbers(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6))), new LottoNumber(7));

        LottoTicketList lottoTicketList = new LottoTicketList(List.of(lottoTicket1, lottoTicket2, lottoTicket3));

        LottoGame lottoGame = new LottoGame(lottoTicketList, winningNumbers);

        LottoResults lottoResults = lottoGame.getLottoTicketsResult();

        assertThat(lottoResults.getResultCount(LottoResultType.FIFTH_PLACE)).isEqualTo(0);
        assertThat(lottoResults.getResultCount(LottoResultType.FOURTH_PLACE)).isEqualTo(0);
        assertThat(lottoResults.getResultCount(LottoResultType.THIRD_PLACE)).isEqualTo(1);
        assertThat(lottoResults.getResultCount(LottoResultType.SECOND_PLACE)).isEqualTo(1);
        assertThat(lottoResults.getResultCount(LottoResultType.FIRST_PLACE)).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률 계산 기능")
    void lottoProfitRateTest() {
        LottoResults lottoResults = new LottoResults();

        for (int i = 0; i < 13; i++)
            lottoResults.countResult(LottoResultType.FAIL);
        lottoResults.countResult(LottoResultType.FIFTH_PLACE);

        assertThat(String.format("%.2f", lottoResults.getProfitRate())).isEqualTo("0.36");
    }
}


