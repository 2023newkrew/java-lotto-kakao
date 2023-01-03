package lotto.repository;

import lotto.model.LottoTicket;
import lotto.model.LottoTickets;

public class LottoRepository {
    private static final LottoTickets lottoTickets = new LottoTickets();

    public void saveLottoTicket(LottoTicket lottoTicket){
        lottoTickets.addLottoTicket(lottoTicket);
    }

    public LottoTickets getAllLottoTicket(){
        return lottoTickets;
    }
}
