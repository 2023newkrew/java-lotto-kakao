package lotto.controller;

import lotto.domain.LottoTickets;

public class LottoController {
    private final LottoTickets tickets;

    public LottoController(int amount) {
        this.tickets = new LottoTickets(amount);
    }

    public void registerRandomLotto(int randomCount){
        this.tickets.createRandomTickets(randomCount);
    }

    public void registerManualLotto(LottoTickets lottoTickets){
        this.tickets.concatTickets(lottoTickets);
    }

    public int getLottoTicketCount() {
        return this.tickets.getLottoTicketCount();
    }

    public LottoTickets getLottoTickets(){
        return this.tickets;
    }
}
