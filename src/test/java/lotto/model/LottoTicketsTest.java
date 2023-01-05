package lotto.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

class LottoTicketsTest {

    private LottoTicket lottoTicket1;
    private LottoTicket lottoTicket2;

    @BeforeEach
    void setUp() {
        this.lottoTicket1 = new LottoTicket(
                Arrays.asList(
                        LottoNumber.valueOf(1),
                        LottoNumber.valueOf(2),
                        LottoNumber.valueOf(3),
                        LottoNumber.valueOf(4),
                        LottoNumber.valueOf(5),
                        LottoNumber.valueOf(6)
                )
        );
        this.lottoTicket2 = new LottoTicket(
                Arrays.asList(
                        LottoNumber.valueOf(1),
                        LottoNumber.valueOf(2),
                        LottoNumber.valueOf(3),
                        LottoNumber.valueOf(4),
                        LottoNumber.valueOf(5),
                        LottoNumber.valueOf(7)
                )
        );
    }

    @Test
    void 자동으로_생성된_티켓의_개수가_입력된_개수와_같아야_함() {
        assertThat(LottoTickets.automaticallyOf(10).stream().count()).isEqualTo(10);
    }

    @Test
    void 다른_티켓들을_합치면_모든_티켓들이_포함되어야_함() {
        LottoTickets lottoTickets1 = new LottoTickets(
                Arrays.asList(
                        lottoTicket1
                )
        );
        LottoTickets lottoTickets2 = new LottoTickets(
                Arrays.asList(
                        lottoTicket2
                )
        );
        lottoTickets1.addAll(lottoTickets2);
        assertThat(lottoTickets1.contains(lottoTicket2)).isTrue();
    }

    @Test
    void 여러장의_복권_당첨_결과_확인() {
        LottoTickets lottoTickets = new LottoTickets(
                Arrays.asList(lottoTicket1, lottoTicket2)
        );

        WinningNumbers winningNumbers = new WinningNumbers(
                lottoTicket1,
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
