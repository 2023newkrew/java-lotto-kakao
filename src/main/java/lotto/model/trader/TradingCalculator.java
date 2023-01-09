package lotto.model.trader;

import lotto.model.prize.Prize;
import lotto.model.prize.PrizeRecord;
import lotto.model.ticket.LottoTicket;

public class TradingCalculator {
    private TradingCalculator() {
        throw new AssertionError();
    }

    public static double calculateYield(PrizeRecord prizeRecord) {
        return (double) TradingCalculator.calculateProfit(prizeRecord) / TradingCalculator.calculateCapital(prizeRecord);
    }

    private static long calculateProfit(PrizeRecord prizeRecord) {
        long profit = 0;
        for (Prize prize : Prize.values()) {
            profit += prizeRecord.getCountOf(prize) * prize.prize();
        }
        return profit;
    }

    private static long calculateCapital(PrizeRecord prizeRecord) {
        int quantity = 0;
        for (Prize prize : Prize.values()) {
            quantity += prizeRecord.getCountOf(prize);
        }
        return LottoTicket.PRICE * quantity;
    }
}
