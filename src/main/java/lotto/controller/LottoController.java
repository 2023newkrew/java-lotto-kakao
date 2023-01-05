package lotto.controller;

import lotto.domain.LottoTickets;

import static lotto.utils.Constants.MIN_PURCHASE_PRICE;

public class LottoController {
    private final LottoTickets lottoTickets;

    public LottoController(int amount) {
        this.lottoTickets = new LottoTickets(amount);
        lottoTickets.createRandomTickets(amount/MIN_PURCHASE_PRICE);
    }

    public int getLottoTicketCount() {
        return lottoTickets.getLottoTicketCount();
    }

    public LottoTickets getLottoTickets(){
        return this.lottoTickets;
    }
}
