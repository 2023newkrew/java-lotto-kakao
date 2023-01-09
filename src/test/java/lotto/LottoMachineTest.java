package lotto;

import lotto.domain.LottoTicket;
import lotto.domain.MatchResult;
import lotto.domain.RandomLottoTicketGenerator;
import lotto.domain.Ranking;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {
    @Test
    void purchaseLottoTickets() {
        RandomLottoTicketGenerator randomLottoGenerator = new RandomLottoTicketGenerator() {
            @Override
            public LottoTicket generate() {
                return new LottoTicket(1, 2, 3, 4, 5, 6);
            }
        };

        LottoMachine lottoMachine = new LottoMachine(randomLottoGenerator);
        List<LottoTicket> purchasedLottos = lottoMachine.purchaseLottoTickets(2000, List.of(List.of(2, 3, 4, 5, 6, 7)));

        assertThat(purchasedLottos).hasSize(2);
        assertThat(purchasedLottos.get(0)).isEqualTo(new LottoTicket(2, 3, 4, 5, 6, 7));
        assertThat(purchasedLottos.get(1)).isEqualTo(new LottoTicket(1, 2, 3, 4, 5, 6));
    }

    @Test
    void match() {
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoTicketGenerator());
        lottoMachine.setWinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        MatchResult matchResult = lottoMachine.match(List.of(
                new LottoTicket(1, 2, 3, 4, 5, 6),
                new LottoTicket(1, 2, 3, 4, 5, 7),
                new LottoTicket(8, 9, 10, 11, 12, 13)
        ));

        assertThat(matchResult.getCount(Ranking.FIRST)).isEqualTo(1);
        assertThat(matchResult.getCount(Ranking.SECOND)).isEqualTo(1);
        assertThat(matchResult.getCount(Ranking.THIRD)).isEqualTo(0);
        assertThat(matchResult.getCount(Ranking.FOURTH)).isEqualTo(0);
        assertThat(matchResult.getCount(Ranking.FIFTH)).isEqualTo(0);
        assertThat(matchResult.getCount(Ranking.OTHER)).isEqualTo(1);
    }
}