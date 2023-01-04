package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTickets {
    List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public LottoTickets(LottoTicketsDto ticketsDto) {
        this.tickets = ticketsDto.getTickets()
                .stream().map(
                        LottoTicket::new
                ).collect(Collectors.toList());
    }

    public static LottoTickets automaticallyOf(int count) {
        List<LottoTicket> tickets = new ArrayList<>();
        LottoTicketAutoGenerator lottoTicketAutoGenerator = LottoTicketAutoGenerator.getInstance();
        for (int i = 0; i < count; i++) {
            tickets.add(lottoTicketAutoGenerator.generate());
        }
        return new LottoTickets(tickets);
    }

    public void addAll(LottoTickets tickets) {
        this.tickets.addAll(tickets.stream().collect(Collectors.toList()));
    }

    public Result getResults(WinningNumbers winningNumbers) {
        Result result = new Result();
        for (LottoTicket ticket : tickets) {
            result.addUp(winningNumbers.match(ticket));
        }
        return result;
    }

    public Stream<LottoTicket> stream() {
        return tickets.stream();
    }
}