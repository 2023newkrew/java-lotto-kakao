package lotto.controller;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

import java.util.List;

public class LottoGame {
    private final LottoTicket lottoTicket;

    public LottoGame(int amount, List<LottoNumber> manual) {
        this.lottoTicket = new LottoTicket(amount, manual);
    }

    public LottoTicket getLottoTickets(){
        return this.lottoTicket;
    }
}
