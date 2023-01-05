package domain.lotto;

import domain.lotto.ticket.LottoTicket;
import domain.lotto.ticket.generator.LottoTicketGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {

    public static final int LOTTO_PRICE = 1000;

    private final LottoTicketGenerator lottoTicketGenerator;

    public LottoStore(LottoTicketGenerator lottoTicketGenerator) {
        this.lottoTicketGenerator = lottoTicketGenerator;
    }

    public List<LottoTicket> buyLottoTickets(Cost cost) {
        validateCost(cost);
        ArrayList<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < cost.countAvailablePurchases(LOTTO_PRICE); i++) {
            lottoTickets.add(lottoTicketGenerator.generate());
        }
        return lottoTickets;
    }

    private void validateCost(Cost cost) {
        if (cost.countAvailablePurchases(LOTTO_PRICE) <= 0) {
            throw new IllegalArgumentException("잔액이 부족합니다");
        }
    }
}
