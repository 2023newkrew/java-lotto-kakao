package lotto.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.domain.strategy.NumberSelectStrategy;

public class LottoDispenser {

    private final NumberSelectStrategy numberSelectStrategy;

    private static final Map<NumberSelectStrategy, LottoDispenser> LOTTO_DISPENSER_MAP
            = new HashMap<>();

    private LottoDispenser(NumberSelectStrategy numberSelectStrategy) {
        this.numberSelectStrategy = numberSelectStrategy;
    }

    public static LottoDispenser from(NumberSelectStrategy numberSelectStrategy) {
        if (!LOTTO_DISPENSER_MAP.containsKey(numberSelectStrategy)) {
            LOTTO_DISPENSER_MAP.put(numberSelectStrategy, new LottoDispenser(numberSelectStrategy));
        }
        return LOTTO_DISPENSER_MAP.get(numberSelectStrategy);
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
