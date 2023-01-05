package lotto.model.ticket;

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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (LottoTicket ticket : this.tickets) {
            sb.append(ticket.toString()).append("\n");
        }
        return sb.toString();
    }
}
