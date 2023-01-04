package lotto.repository;

import lotto.model.LottoTicket;
import lotto.model.LottoTickets;
public class LottoRepository {
    private static final LottoTickets lottoTickets = new LottoTickets();

    private LottoRepository() {
    }

    public static void saveLottoTicket(LottoTicket lottoTicket) {
        lottoTickets.addLottoTicket(lottoTicket);
    }

    public static LottoTickets getAllLottoTicket() {
        return lottoTickets;
    }

    public static void resetLottoTickets() {
        lottoTickets.resetLottoTickets();
    }
}
