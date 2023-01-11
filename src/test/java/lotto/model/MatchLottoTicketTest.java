package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchLottoTicketTest {
    @Test
    void 복권_당첨_결과_확인() {
        LottoTickets lottoTickets = new LottoTickets(
                Arrays.asList(
                        new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6)),
                        new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 7))
                )
        );

        LottoTicket winningNumbers = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6), 7);

        assertThat(MatchLottoTicket.matchLotto(lottoTickets, winningNumbers)).isEqualTo(new Result(
                new HashMap<>() {{
                    put(Grade.SIX, 1);
                    put(Grade.FIVE_BONUS, 1);
                }}
        ));
    }
}
