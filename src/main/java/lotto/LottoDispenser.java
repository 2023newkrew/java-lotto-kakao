package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoDispenser {
    private final static int TICKET_PRICE = 1000;

    public List<LottoTicket> getLottoTicket(int money) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        int ticketQuantity = calculateTicketQuantity(money);
        for (int i = 0; i < ticketQuantity; i++) {
            lottoTickets.add(new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6)));
        }
        return lottoTickets;
    }

    private int calculateTicketQuantity(int money) {
        return money / TICKET_PRICE;
    }
}
