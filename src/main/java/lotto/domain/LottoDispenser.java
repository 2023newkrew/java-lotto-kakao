package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoDispenser {

    private final NumberSelectStrategy numberSelectStrategy;

    public LottoDispenser(NumberSelectStrategy numberSelectStrategy) {
        this.numberSelectStrategy = numberSelectStrategy;
    }

    public LottoTicketList getLottoTicket(int money) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        int ticketQuantity = calculateTicketQuantity(money);
        for (int i = 0; i < ticketQuantity; i++) {
            lottoTickets.add(new LottoTicket(numberSelectStrategy.select()));
        }
        return new LottoTicketList(lottoTickets);
    }

    private int calculateTicketQuantity(int money) {
        return money / LottoTicket.LOTTO_TICKET_PRICE;
    }
}
