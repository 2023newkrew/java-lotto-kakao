package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

class LottoTicketsTest {
    @Test
    void 여러장의_복권_당첨_결과_확인() {
        LottoTickets lottoTickets = new LottoTickets(
                Arrays.asList(
                        new LottoTicket(
                                Arrays.asList(
                                        new LottoNumber(1),
                                        new LottoNumber(2),
                                        new LottoNumber(3),
                                        new LottoNumber(4),
                                        new LottoNumber(5),
                                        new LottoNumber(6)
                                )
                        ),
                        new LottoTicket(
                                Arrays.asList(
                                        new LottoNumber(1),
                                        new LottoNumber(2),
                                        new LottoNumber(3),
                                        new LottoNumber(4),
                                        new LottoNumber(5),
                                        new LottoNumber(7)
                                )
                        )
                )
        );

        WinningNumbers winningNumbers = new WinningNumbers(
                "1, 2, 3, 4, 5, 6",
                "7"
        );

        assertThat(lottoTickets.getResults(winningNumbers)).isEqualTo(new Result(
                new HashMap<>() {{
                    put(Grade.SIX, 1);
                    put(Grade.FIVE_BONUS, 1);
                }}
        ));
    }
}
