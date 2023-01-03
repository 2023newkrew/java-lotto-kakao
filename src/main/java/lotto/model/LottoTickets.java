package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = new ArrayList<>(tickets);
    }

    public static LottoTickets of(int count) {
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
            result.addUp(key);
        }
        return result;
    }

    public List<LottoTicket> getTicket() {
        return new ArrayList<>(tickets);
    }

}