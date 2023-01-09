package lotto.model.publisher;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;

import java.util.List;

public class TicketGenerator {
    private TicketingStrategy strategy;

    public TicketGenerator(TicketingStrategy strategy) {
        this.strategy = strategy;
    }

    public LottoTicket generate(List<LottoNumber>... numbers) {
        return this.strategy.generate(numbers);
    }
}
