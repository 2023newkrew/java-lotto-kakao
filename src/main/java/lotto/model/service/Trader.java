package lotto.model.service;

import lotto.model.prize.PrizeJudge;
import lotto.model.prize.PrizeRecord;
import lotto.model.prize.WinningNumbers;
import lotto.model.ticket.LottoTicket;
import lotto.model.ticket.LottoTickets;

public class Trader {
    private final Cash cash;
    private final LottoTickets purchasedTickets;
    private final PrizeRecord prizeRecord;

    public Trader(long capital) {
        this.cash = new Cash(capital);
        this.purchasedTickets = new LottoTickets();
        this.prizeRecord = new PrizeRecord();
    }

    public void purchaseLotto(LottoTickets tickets) {
        this.cash.pay(LottoTicket.PRICE * tickets.size());
        this.purchasedTickets.add(tickets);
    }

    public PrizeRecord confirmResult(WinningNumbers winningNumbers) {
        for (LottoTicket ticket : this.purchasedTickets.getTickets()) {
            this.prizeRecord.addCountOf(
                    PrizeJudge.getPrizeOf(
                            winningNumbers.matchNumbers(ticket),
                            winningNumbers.hasBonus(ticket)));
        }
        return this.prizeRecord;
    }
}
