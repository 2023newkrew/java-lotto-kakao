package lotto.model;

import java.util.List;

public class LottoTickets {
    List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = tickets;
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