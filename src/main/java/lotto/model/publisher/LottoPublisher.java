package lotto.model.publisher;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTickets;

import java.util.List;

public class LottoPublisher {
    private final TicketGenerator ticketGenerator;
    private final RandomNumbersGenerator randomNumbersGenerator;

    public LottoPublisher() {
        this.ticketGenerator = new TicketGenerator();
        this.randomNumbersGenerator = new RandomNumbersGenerator();
    }

    public LottoTickets publishRandomLotto(int quantity) {
        LottoTickets purchasedTickets = new LottoTickets();
        for (int i = 0; i < quantity; i++) {
            purchasedTickets.add(this.ticketGenerator.generate(randomNumbersGenerator.getOrderedNumbers()));
        }
        return purchasedTickets;
    }

    public LottoTickets publishManualLotto(List<List<LottoNumber>> givenNumbersList) {
        LottoTickets purchasedTickets = new LottoTickets();
        for (List<LottoNumber> numbers : givenNumbersList) {
            purchasedTickets.add(this.ticketGenerator.generate(numbers));
        }
        return purchasedTickets;
    }
}
