package lotto.controller;

import lotto.domain.LottoTickets;

public class LottoController {
    private final LottoTickets lottoTickets;

    public LottoController(int count) {
        this.lottoTickets = new LottoTickets(count);
    }

    public int getLottoTicketCount() {
        return lottoTickets.getLottoTicketCount();
    }
}
