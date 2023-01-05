package lotto.controller;

import lotto.domain.LottoTicket;

public class LottoGame {
    private final LottoTicket lottoTicket;

    public LottoGame(int amount) {
        this.lottoTicket = new LottoTicket(amount);
    }

    public LottoTicket getLottoTickets(){
        return this.lottoTicket;
    }
}
