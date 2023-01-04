package domain.lotto;

import domain.lotto.result.LottoResults;
import domain.lotto.ticket.LottoTicket;
import domain.lotto.ticket.LottoTicketList;

public class LottoGame {

    private final LottoTicketList lottoTicketList;
    private final WinningNumbers winningNumbers;

    public LottoGame(LottoTicketList lottoTicketList, WinningNumbers winningNumbers) {
        this.lottoTicketList = lottoTicketList;
        this.winningNumbers = winningNumbers;
    }

    public LottoResults getLottoTicketsResult() {
        LottoResults lottoResults = new LottoResults();
        for (LottoTicket lottoTicket : lottoTicketList.getLottoTickets()) {
            lottoResults.countResult(LottoChecker.getResult(lottoTicket, winningNumbers));
        }
        return lottoResults;
    }
}
