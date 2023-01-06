package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTickets {
    private final List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public static LottoTickets fromNumberLists(List<List<Integer>> ticketsRaw) {
        return new LottoTickets(ticketsRaw.stream()
                .map(LottoTicket::fromNumbers)
                .collect(Collectors.toList())
        );
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
        this.tickets.addAll(tickets.getTickets());
    }

    public Result getResults(WinningNumbers winningNumbers) {
        Result result = new Result();
        for (LottoTicket ticket : tickets) {
            result.addUp(winningNumbers.match(ticket));
        }
        return result;
    }

    public boolean contains(LottoTicket ticket) {
        return tickets.contains(ticket);
    }

    public Stream<LottoTicket> stream() {
        return tickets.stream();
    }

    public List<LottoTicket> getTickets() {
        return new ArrayList<>(tickets);
    }

    @Override
    public String toString() {
        return "LottoTickets{" +
                "tickets=" + tickets +
                '}';
    }
}