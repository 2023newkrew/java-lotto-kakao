package lotto.view;

import lotto.model.prize.Prize;
import lotto.model.prize.PrizeRecord;
import lotto.model.ticket.LottoTickets;

import java.util.Arrays;

public class OutputView {
    private OutputView() {
        throw new AssertionError();
    }

    public static void displayPurchasedTickets(int automaticQuantity, LottoTickets tickets) {
        System.out.printf("수동으로 %d장, 자동으로 %d장을 구매했습니다.\n", tickets.size() - automaticQuantity, automaticQuantity);
        System.out.println(tickets);
    }

    public static void displayStatics(PrizeRecord prizeRecord) {
        System.out.println("\n당첨 통계\n---------");
        for (Prize prize : Arrays.copyOfRange(Prize.values(), 1, Prize.values().length)) {
            System.out.printf("%s (%d원) - %d개\n",
                    prize.matchDescription(),
                    prize.prize(),
                    prizeRecord.getCountOf(prize));
        }
    }

    public static void displayYield(double yield) {
        System.out.printf("총 수익률은 %.2f입니다.", yield);
    }
}
