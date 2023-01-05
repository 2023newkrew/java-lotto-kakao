import domain.lotto.number.LottoNumbers;
import domain.lotto.number.WinningNumbers;
import domain.lotto.result.LottoResultType;
import domain.lotto.result.LottoResults;
import domain.lotto.ticket.LottoTickets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoResultsTest {

    private LottoResults lottoResults;

    @BeforeEach
    void setUp() {
        LottoNumbers lottoTicket1 = new LottoNumbers(List.of(1, 2, 3, 40, 41, 42));  //5등
        LottoNumbers lottoTicket2 = new LottoNumbers(List.of(1, 2, 4, 40, 41, 42));  //5등
        LottoNumbers lottoTicket3 = new LottoNumbers(List.of(1, 2, 3, 4, 40, 41));  //4등

        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        LottoTickets lottoTickets = new LottoTickets(List.of(lottoTicket1, lottoTicket2, lottoTicket3));

        lottoResults = new LottoResults(lottoTickets, winningNumbers);
    }

    @Test
    @DisplayName("여러 장의 로또 용지의 당첨 결과를 반환하는 기능")
    void getManyLottoTicketsResult() {
        assertThat(lottoResults.getResultCount(LottoResultType.FIFTH_PLACE)).isEqualTo(2);
        assertThat(lottoResults.getResultCount(LottoResultType.FOURTH_PLACE)).isEqualTo(1);
        assertThat(lottoResults.getResultCount(LottoResultType.THIRD_PLACE)).isEqualTo(0);
        assertThat(lottoResults.getResultCount(LottoResultType.SECOND_PLACE)).isEqualTo(0);
        assertThat(lottoResults.getResultCount(LottoResultType.FIRST_PLACE)).isEqualTo(0);
    }

    @Test
    @DisplayName("수익률 계산 기능")
    void lottoProfitRateTest() {
        // 투자금 : 3000, 수익: 60000
        assertThat(lottoResults.getProfitRate()).isEqualTo(20.00f);
    }
}


