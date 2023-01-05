package lotto.view;

import lotto.model.prize.Prize;
import lotto.model.prize.PrizeRecord;
import lotto.model.ticket.LottoTickets;

import java.util.Arrays;

public class OutputView {

    public static void displayPurchasedTickets(LottoTickets tickets) {
        System.out.printf("%d개를 구매했습니다.\n", tickets.size());
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
