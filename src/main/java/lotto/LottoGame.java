package lotto;

import java.util.List;

public class LottoGame {

    private final LottoDispenser lottoDispenser;

    public LottoGame(NumberSelectStrategy numberSelectStrategy) {
        this.lottoDispenser = new LottoDispenser(numberSelectStrategy);
    }

    public int buy(int money) {
        List<LottoTicket> lottoTickets = lottoDispenser.getLottoTicket(money);
        return lottoTickets.size();
    }
}
