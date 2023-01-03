package domain.lotto;

import domain.lotto.result.LottoResults;
import domain.lotto.ticket.LottoTicket;
import domain.lotto.ticket.LottoTickets;

import java.util.List;

public class LottoGame {

    private final LottoTickets lottoTickets;
    private final WinningNumbers winningNumbers;

    public LottoGame(LottoTickets lottoTickets, WinningNumbers winningNumbers) {
        this.lottoTickets = lottoTickets;
        this.winningNumbers = winningNumbers;
    }

    public LottoResults getLottoTicketsResult() {
        LottoResults lottoResults = new LottoResults();
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            lottoResults.countResult(LottoChecker.getResult(lottoTicket, winningNumbers));
        }
        return lottoResults;
    }
}
