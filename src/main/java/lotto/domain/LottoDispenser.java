package lotto.domain;

import lotto.strategy.NumberSelectStrategy;

import java.util.ArrayList;
import java.util.List;

public class LottoDispenser {

    private final NumberSelectStrategy numberSelectStrategy;

    public LottoDispenser(NumberSelectStrategy numberSelectStrategy) {
        this.numberSelectStrategy = numberSelectStrategy;
    }

    public LottoTicketsManager getLottoTicket(int money) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        int ticketQuantity = calculateTicketQuantity(money);
        for (int i = 0; i < ticketQuantity; i++) {
            lottoTickets.add(new LottoTicket(numberSelectStrategy.select()));
        }
        return new LottoTicketsManager(lottoTickets);
    }

    private int calculateTicketQuantity(int money) {
        return money / LottoTicket.LOTTO_TICKET_PRICE;
    }
}
