package lotto.model.service;

import lotto.model.prize.Prize;
import lotto.model.prize.PrizeRecord;
import lotto.model.ticket.LottoTicket;

public class PrizeExchange {
    public double calculateYield(PrizeRecord prizeRecord) {
        return (double) this.calculateProfit(prizeRecord) / this.calculateCapital(prizeRecord);
    }

    private long calculateProfit(PrizeRecord prizeRecord) {
        long profit = 0;
        for (Prize prize : Prize.values()) {
            profit += prizeRecord.getCountOf(prize) * prize.prize();
        }
        return profit;
    }

    private long calculateCapital(PrizeRecord prizeRecord) {
        int quantity = 0;
        for (Prize prize : Prize.values()) {
            quantity += prizeRecord.getCountOf(prize);
        }
        return LottoTicket.PRICE * quantity;
    }
}
