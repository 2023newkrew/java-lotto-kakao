package lotto.model.publisher;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;

import java.util.List;

public interface TicketingStrategy {
    public LottoTicket generate(List<LottoNumber>... number);
}
