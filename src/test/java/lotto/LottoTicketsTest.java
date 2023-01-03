package lotto;

import java.util.Arrays;
import lotto.domain.enumeration.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.LottoWinningNumbers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

    @DisplayName("로또 티켓 번호 일괄 확인")
    @Test
    public void get_lotto_numbers_string() {
        LottoTickets lottoTickets = new LottoTickets(Arrays.asList(
                new LottoTicket(Arrays.asList(1,2,3,4,5,6)),
                new LottoTicket(Arrays.asList(3,6,15,21,38,41))
        ));
        Assertions.assertThat(lottoTickets.getLottoNumbersString())
                .isEqualTo("[1, 2, 3, 4, 5, 6]\n[3, 6, 15, 21, 38, 41]");
    }

    @DisplayName("당첨 통계 계산")
    @Test
    public void get_lotto_numbers_statistics() {
        LottoWinningNumbers lottoWinningNumbers = new LottoWinningNumbers(
                Arrays.asList(1, 2, 3, 4, 5, 6), 7
        );
        LottoTickets lottoTickets = new LottoTickets(Arrays.asList(
                createCustomTicket(LottoResult.FIRST),
                createCustomTicket(LottoResult.FOURTH),
                createCustomTicket(LottoResult.FIFTH)
        ));
        Assertions.assertThat(lottoTickets.getStatistics(lottoWinningNumbers).getString())
                .isEqualTo("3개 일치 (5000원) - 1개\n"
                        + "4개 일치 (50000원) - 1개\n"
                        + "5개 일치 (1500000원) - 0개\n"
                        + "5개 일치, 보너스 볼 일치(30000000원) - 0개\n"
                        + "6개 일치 (2000000000원) - 1개\n"
                        + "총 수익률은 666685.00입니다.");
    }

    private LottoTicket createCustomTicket(LottoResult lottoResult) {
        return new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6)) {
            @Override
            public LottoResult getResult(LottoWinningNumbers lottoWinningNumbers) {
                return lottoResult;
            }
        };
    }
}
