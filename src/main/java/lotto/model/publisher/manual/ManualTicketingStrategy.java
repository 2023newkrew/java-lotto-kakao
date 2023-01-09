package lotto.model.publisher.manual;

import lotto.model.publisher.TicketingStrategy;
import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;

import java.util.List;

public class ManualTicketingStrategy implements TicketingStrategy {
    public LottoTicket generate(List<LottoNumber>... numbers) {
        if (numbers.length != 1) {
            throw new IllegalArgumentException();
        }
        return new LottoTicket(numbers[0]);
    }
}
