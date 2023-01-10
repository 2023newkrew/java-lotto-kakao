package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {
    private final List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public static LottoTickets countOf(ArrayList<String> manual, int count) {
        List<LottoTicket> tickets = manual.stream().map(lotto -> new LottoTicket(lotto, "")).collect(Collectors.toList());
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
