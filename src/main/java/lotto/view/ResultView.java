package lotto.view;

import lotto.model.*;

import java.util.stream.Collectors;

public class ResultView {
    private static ResultView instance;

    private ResultView() {
    }

    public static ResultView getInstance() {
        if (instance == null) {
            instance = new ResultView();
        }
        return instance;
    }

    public void printLottoTickets(LottoTicketsDto tickets) {
        System.out.printf("%d개 구매했습니다.\n", tickets.size());
        for (LottoTicketDto ticket : tickets.getTickets()) {
            printLottoTicket(ticket);
        }
        System.out.println();
    }

    private void printLottoTicket(LottoTicketDto ticketDto) {
        String numbers = ticketDto.getTicket().stream()
                .sorted().map(
                number -> Integer.toString(number)
        ).collect(Collectors.joining(", "));
        System.out.println("[" + numbers + "]");
    }

    public void printResultStatistics(Result result, int money) {
        System.out.printf("\n당첨 통계\n" +
                        "--------\n" +
                        "3개 일치 (5000원) - %d개\n" +
                        "4개 일치 (50000원) - %d개\n" +
                        "5개 일치 (1500000원) - %d개\n" +
                        "5개 일치, 보너스 볼 일치(30000000원) - %d개\n" +
                        "6개 일치 (2000000000원) - %d개\n" +
                        "총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)\n",
                result.getValue(Grade.THREE),
                result.getValue(Grade.FOUR),
                result.getValue(Grade.FIVE),
                result.getValue(Grade.FIVE_BONUS),
                result.getValue(Grade.SIX),
                result.getProfitRate(money),
                result.getProfitRate(money) > 1 ? "이익이" : "손해"
        );
    }
}
