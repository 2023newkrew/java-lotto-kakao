package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private final List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public static LottoTickets countOf(int count) {
        List<LottoTicket> tickets = new ArrayList<>();
        LottoTicketGenerator lottoTicketGenerator = LottoTicketGenerator.getInstance();
        for (int i = 0; i < count; i++) {
            tickets.add(lottoTicketGenerator.generate());
        }
        return new LottoTickets(tickets);
    }

    public List<LottoTicket> getTicket() {
        return new ArrayList<>(tickets);
    }
}
