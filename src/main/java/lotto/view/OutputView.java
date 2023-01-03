package lotto.view;

import lotto.model.Lotto;
import lotto.model.Prize;
import lotto.model.PrizeRecord;
import lotto.model.Ticket;

import java.util.Arrays;

public class OutputView {

    private static String generateTicketString(Ticket ticket) {
        StringBuilder sb = new StringBuilder("[");
        for (int number : ticket.getNumbers()) {
            sb.append(number);
            sb.append(", ");
        }
        return sb.substring(0, sb.length() - 2) + "]";
    }

    public static void sendPurchasedLotto(Lotto lotto) {
        System.out.printf("%d개를 구매했습니다.\n", lotto.getQuantity());
        for (Ticket ticket : lotto.getTickets()) {
            System.out.println(OutputView.generateTicketString(ticket));
        }
    }

    public static void sendStatics(PrizeRecord prizeRecord) {
        System.out.println("\n당첨 통계\n---------");
        for (Prize prize : Arrays.copyOfRange(Prize.values(), 1, Prize.values().length)) {
            System.out.printf("%s (%d원) - %d개\n",
                    prize.matchDescription(),
                    prize.prize(),
                    prizeRecord.getCountOf(prize));
        }
    }

    public static void sendYield(double yield) {
        System.out.printf("총 수익률은 %.2f입니다.", yield);
    }
}
