package lotto.controller;

import lotto.domain.LottoTickets;

public class LottoController {
    private final LottoTickets lottoTickets;

    public LottoController(int amount) {
        this.lottoTickets = new LottoTickets(amount);
    }

    public LottoTickets getLottoTickets(){
        return this.lottoTickets;
    }
}
