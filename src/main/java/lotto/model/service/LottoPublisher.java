package lotto.model.service;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTickets;

import java.util.List;

public class LottoPublisher {
    private final TicketGenerator ticketGenerator;

    public LottoPublisher() {
        this.ticketGenerator = new TicketGenerator();
    }

    public LottoTickets publishRandomLotto(int quantity) {
        LottoTickets purchasedTickets = new LottoTickets();
        for (int i = 0; i < quantity; i++) {
            purchasedTickets.add(this.ticketGenerator.generateTicket());
        }
        return purchasedTickets;
    }

    public LottoTickets publishManualLotto(List<List<LottoNumber>> givenNumbersList) {
        LottoTickets purchasedTickets = new LottoTickets();
        for (List<LottoNumber> numbers : givenNumbersList) {
            purchasedTickets.add(this.ticketGenerator.generateTicket(numbers));
        }
        return purchasedTickets;
    }
}
