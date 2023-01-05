package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private final List<LottoTicket> tickets = new ArrayList<>();

    public int size() {
        return this.tickets.size();
    }

    public void add(LottoTicket ticket) {
        this.tickets.add(ticket);
    }

    public void add(LottoTickets tickets) {
        this.tickets.addAll(tickets.tickets);
    }
}
