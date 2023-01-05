package lotto.controller;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

import static lotto.utils.Constants.MIN_PURCHASE_PRICE;

public class LottoController {
    private final LottoTickets Tickets;

    public LottoController(int amount) {
        this.Tickets = new LottoTickets(amount);
    }

    public void registerRandomLotto(int amount){
        this.Tickets.createRandomTickets(amount/MIN_PURCHASE_PRICE);
    }

    public void registerManualLotto(LottoTickets lottoTickets){
        int loopCount = lottoTickets.getTickets().size();
        for (int i=0; i < loopCount; i++){
            LottoTicket lottoTicket = lottoTickets.getTickets().get(i);
            this.Tickets.createManualTicket(lottoTicket);
        }
    }

    public int getLottoTicketCount() {
        return Tickets.getLottoTicketCount();
    }

    public LottoTickets getLottoTickets(){
        return this.Tickets;
    }
}
