package lotto.model.publisher;

import lotto.model.publisher.automatic.AutomaticTicketingStrategy;
import lotto.model.publisher.manual.ManualTicketingStrategy;
import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTickets;

import java.util.List;

public class LottoPublisher {
    private final TicketGenerator automaticTicketGenerator;
    private final TicketGenerator manualTicketGenerator;

    public LottoPublisher() {
        this.automaticTicketGenerator = new TicketGenerator(new AutomaticTicketingStrategy());
        this.manualTicketGenerator = new TicketGenerator(new ManualTicketingStrategy());
    }

    public LottoTickets publishRandomLotto(int quantity) {
        LottoTickets purchasedTickets = new LottoTickets();
        for (int i = 0; i < quantity; i++) {
            purchasedTickets.add(this.automaticTicketGenerator.generate());
        }
        return purchasedTickets;
    }

    public LottoTickets publishManualLotto(List<List<LottoNumber>> givenNumbersList) {
        LottoTickets purchasedTickets = new LottoTickets();
        for (List<LottoNumber> numbers : givenNumbersList) {
            purchasedTickets.add(this.manualTicketGenerator.generate(numbers));
        }
        return purchasedTickets;
    }
}
