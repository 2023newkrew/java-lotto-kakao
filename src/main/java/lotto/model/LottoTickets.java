package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LottoTickets {
    List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public static LottoTickets automaticallyOf(int count) {
        List<LottoTicket> tickets = new ArrayList<>();
        LottoTicketAutoGenerator lottoTicketAutoGenerator = LottoTicketAutoGenerator.getInstance();
        for (int i = 0; i < count; i++) {
            tickets.add(lottoTicketAutoGenerator.generate());
        }
        return new LottoTickets(tickets);
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