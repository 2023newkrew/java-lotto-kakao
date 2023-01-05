package lotto.view;

import lotto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class OutputViewTest {
    @Test
    @DisplayName("로또 출력")
    void printLottoTickets() {
        RandomLottoTicketGenerator randomLottoTicketGenerator = new RandomLottoTicketGenerator();
        List<LottoTicket> lottoTickets = List.of(
                randomLottoTicketGenerator.generate(),
                randomLottoTicketGenerator.generate(),
                randomLottoTicketGenerator.generate()
        );

        OutputView outputView = new OutputView();
        outputView.printLottoTickets(lottoTickets, 0);
    }

    @Test
    @DisplayName("로또 당첨 결과 출력")
    void printMatchResult() {
        Map<Ranking, Long> rankingCount = Map.of(
                Ranking.FIFTH, 1L,
                Ranking.OTHER, 3L
        );
        MatchResult matchResult = new MatchResult(rankingCount);

        OutputView outputView = new OutputView();
        outputView.printMatchResult(matchResult);

        // 구입한 로또: 4개(4000원)
        // 당첨금: 5000원
        // 수익률: 1.25
    }
}