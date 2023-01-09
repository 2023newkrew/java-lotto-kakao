package lotto.controller;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

import java.util.List;

public class LottoGame {
    private final LottoTickets lottoTickets;

    public LottoGame(int amount, List<LottoTicket> manual) {
        this.lottoTickets = new LottoTickets(amount, manual);
    }

    public LottoTickets getLottoTickets(){
        return this.lottoTickets;
    }
}
