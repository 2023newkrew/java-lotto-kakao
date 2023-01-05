package lotto.domain;

import lotto.strategy.AutoNumberSelectStrategy;

import java.util.ArrayList;
import java.util.List;

public class LottoDispenser {

    private final AutoNumberSelectStrategy autoNumberSelectStrategy;

    public LottoDispenser(AutoNumberSelectStrategy autoNumberSelectStrategy) {
        this.autoNumberSelectStrategy = autoNumberSelectStrategy;
    }

    public LottoTicketsManager getLottoTickets(long money, List<LottoNumberList> manualLottoNumberList) {
        long manualTicketQuantity = manualLottoNumberList.size();
        long autoTicketQuantity = calculateTicketQuantity(money, manualTicketQuantity);

        List<LottoTicket> lottoTickets = new ArrayList<>();
        addAutoLottoTickets(lottoTickets, autoTicketQuantity);
        addManualLottoTickets(lottoTickets, manualLottoNumberList);
        return new LottoTicketsManager(lottoTickets);
    }

    private void addAutoLottoTickets(List<LottoTicket> lottoTickets, long autoTicketQuantity) {
        for (int i = 0; i < autoTicketQuantity; i++) {
            lottoTickets.add(new LottoTicket(autoNumberSelectStrategy.selectNumbers()));
        }
    }

    private void addManualLottoTickets(List<LottoTicket> lottoTickets, List<LottoNumberList> manualLottoNumbers) {
        for (LottoNumberList lottoNumberList : manualLottoNumbers) {
            lottoTickets.add(new LottoTicket(lottoNumberList));
        }
    }

    private long calculateTicketQuantity(long money, long manualLottoQuantity) {
        return (money / LottoTicket.LOTTO_TICKET_PRICE) - manualLottoQuantity;
    }
}
