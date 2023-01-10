package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private final List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }
    
    public List<LottoTicket> getTicket() {
        return new ArrayList<>(tickets);
    }
}
