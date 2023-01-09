package lotto.domain;

import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private final List<LottoTicket> ticket;

    public LottoTickets(List<LottoTicket> auto, List<LottoTicket> manual) {
        this.ticket = auto;
        this.ticket.addAll(manual);
    }

    public int getLottoTicketSize(){
        return ticket.size();
    }

    public List<LottoTicket> getTicket(){
        return Collections.unmodifiableList(this.ticket);
    }
}
