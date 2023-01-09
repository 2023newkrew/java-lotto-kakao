package lotto.domain;

import lotto.strategy.NumberSelectStrategy;

import java.util.ArrayList;
import java.util.List;

public class LottoDispenser {

    private final NumberSelectStrategy numberSelectStrategy;

    public LottoDispenser(NumberSelectStrategy numberSelectStrategy) {
        this.numberSelectStrategy = numberSelectStrategy;
    }

    public LottoTicketsManager getLottoTicket(long money) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        long ticketQuantity = calculateTicketQuantity(money);
        for (int i = 0; i < ticketQuantity; i++) {
            lottoTickets.add(new LottoTicket(numberSelectStrategy.selectNumbers()));
        }
        return new LottoTicketsManager(lottoTickets);
    }

    private long calculateTicketQuantity(long money) {
        return money / LottoTicket.LOTTO_TICKET_PRICE;
    }
}
