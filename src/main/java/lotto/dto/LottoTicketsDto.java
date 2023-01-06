package lotto.dto;

import java.util.List;

public class LottoTicketsDto {
    private final List<List<Integer>> tickets;

    public LottoTicketsDto(List<List<Integer>> tickets) {
        this.tickets = tickets;
    }

    public int size() {
        return tickets.size();
    }

    public List<List<Integer>> getTickets() {
        return tickets;
    }

    @Override
    public String toString() {
        return "LottoTicketsDto{" +
                "tickets=" + tickets +
                '}';
    }
}
