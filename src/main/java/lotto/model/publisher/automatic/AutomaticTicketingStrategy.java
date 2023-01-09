package lotto.model.publisher.automatic;

import lotto.model.publisher.TicketingStrategy;
import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;

import java.util.List;

public class AutomaticTicketingStrategy implements TicketingStrategy {
    private final RandomNumbersGenerator randomNumbersGenerator;

    public AutomaticTicketingStrategy() {
        this.randomNumbersGenerator = new RandomNumbersGenerator();
    }

    public LottoTicket generate(List<LottoNumber>... empty) {
        if (empty.length > 0) {
            throw new IllegalArgumentException();
        }
        return new LottoTicket(this.randomNumbersGenerator.getOrderedNumbers());
    }
}
