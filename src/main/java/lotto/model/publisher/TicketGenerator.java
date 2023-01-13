package lotto.model.publisher;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;

import java.util.List;

public class TicketGenerator {
    public LottoTicket generate(List<LottoNumber> numbers) {
        return new LottoTicket(numbers);
    }
}
