package lotto.view;

import lotto.model.*;

import java.util.Arrays;

public class OutputView {

    private static String generateTicketString(LottoTicket ticket) {
        StringBuilder sb = new StringBuilder("[");
        for (LottoNumber number : ticket.getNumbers()) {
            sb.append(number.getNumber());
            sb.append(", ");
        }
        return sb.substring(0, sb.length() - 2) + "]";
    }

    public static void sendPurchasedLotto(Lotto lotto) {
        System.out.printf("%d개를 구매했습니다.\n", lotto.getQuantity());
        for (LottoTicket ticket : lotto.getTickets()) {
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
