package lotto.domain;

import java.util.ArrayList;

public class LottoTickets {
    private final ArrayList<LottoTicket> tickets;

    public LottoTickets(int count) {
        count /= 1000;
        this.tickets = new ArrayList<>(count);

        for (int i=0; i<count;i++){  // stream 으로 변경 필요
            tickets.add(new LottoTicket());
        }
    }

    public int getLottoTicketCount(){
        return tickets.size();
    }
}
