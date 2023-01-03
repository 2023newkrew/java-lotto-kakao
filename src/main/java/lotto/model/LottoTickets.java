package lotto.model;

import java.util.List;

public class LottoTickets {
    List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = tickets;
    }

    public static LottoTickets countOf(int count) {
        List<LottoTicket> tickets = new ArrayList<>();
        LottoTicketGenerator lottoTicketGenerator = LottoTicketGenerator.getInstance();
        for (int i = 0; i < count; i++) {
            tickets.add(lottoTicketGenerator.generate());
        }
        return new LottoTickets(tickets);
    }

    public Result getResults(WinningNumbers winningNumbers) {
        Result result = new Result();
        Grade key;
        for (LottoTicket ticket : tickets) {
            key = winningNumbers.matchValues(ticket);
            result.put(key, result.get(key) + 1);
        }
        return result;
    }
}