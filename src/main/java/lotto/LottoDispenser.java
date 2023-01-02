package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoDispenser {
    private final static int TICKET_PRICE = 1000;

    private final NumberSelectStrategy numberSelectStrategy;

    public LottoDispenser(NumberSelectStrategy numberSelectStrategy) {
        this.numberSelectStrategy = numberSelectStrategy;
    }

    public List<LottoTicket> getLottoTicket(int money) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        int ticketQuantity = calculateTicketQuantity(money);
        for (int i = 0; i < ticketQuantity; i++) {
            lottoTickets.add(new LottoTicket(numberSelectStrategy.select()));
        }
        return lottoTickets;
    }

    private int calculateTicketQuantity(int money) {
        return money / TICKET_PRICE;
    }
}
