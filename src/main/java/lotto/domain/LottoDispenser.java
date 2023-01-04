package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.strategy.NumberSelectStrategy;

public class LottoDispenser {

    private final NumberSelectStrategy numberSelectStrategy;

    public LottoDispenser(NumberSelectStrategy numberSelectStrategy) {
        this.numberSelectStrategy = numberSelectStrategy;
    }

    public LottoTicketList getLottoTicket(int money) {
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        int ticketQuantity = calculateTicketQuantity(money);
        for (int i = 0; i < ticketQuantity; i++) {
            LottoTicket lottoTicket = new LottoTicket(numberSelectStrategy.select());
            lottoTicketList.add(lottoTicket);
        }
        return new LottoTicketList(lottoTicketList);
    }

    private int calculateTicketQuantity(int money) {
        return money / LottoTicket.LOTTO_TICKET_PRICE;
    }
}
