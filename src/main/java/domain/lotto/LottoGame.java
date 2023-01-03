package domain.lotto;

import domain.lotto.number.WinningNumbers;
import domain.lotto.result.LottoChecker;
import domain.lotto.result.LottoResults;
import domain.lotto.ticket.LottoTicket;
import domain.lotto.ticket.LottoTickets;

public class LottoGame {
    private final LottoTickets lottoTickets;
    private final WinningNumbers winningNumbers;

    public LottoGame(final LottoTickets lottoTickets, final WinningNumbers winningNumbers) {
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
