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
                                        LottoNumber.valueOf(1),
                                        LottoNumber.valueOf(2),
                                        LottoNumber.valueOf(3),
                                        LottoNumber.valueOf(4),
                                        LottoNumber.valueOf(5),
                                        LottoNumber.valueOf(6)
                                )
                        ),
                        new LottoTicket(
                                Arrays.asList(
                                        LottoNumber.valueOf(1),
                                        LottoNumber.valueOf(2),
                                        LottoNumber.valueOf(3),
                                        LottoNumber.valueOf(4),
                                        LottoNumber.valueOf(5),
                                        LottoNumber.valueOf(7)
                                )
                        )
                )
        );


        WinningNumbers winningNumbers = new WinningNumbers(
                new LottoTicket(
                        Arrays.asList(
                                LottoNumber.valueOf(1),
                                LottoNumber.valueOf(2),
                                LottoNumber.valueOf(3),
                                LottoNumber.valueOf(4),
                                LottoNumber.valueOf(5),
                                LottoNumber.valueOf(6)
                        )
                ),
                LottoNumber.valueOf(7)
        );

        assertThat(lottoTickets.getResults(winningNumbers)).isEqualTo(new Result(
                new HashMap<>() {{
                    put(Grade.SIX, 1);
                    put(Grade.FIVE_BONUS, 1);
                }}
        ));
    }
}
