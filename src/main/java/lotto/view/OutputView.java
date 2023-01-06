package lotto.view;

import lotto.dto.LottoTicketsDto;
import lotto.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static OutputView instance;

    private OutputView() {
    }

    public static OutputView getInstance() {
        if (instance == null) {
            instance = new OutputView();
        }
        return instance;
    }

    public void printLottoTickets(LottoTicketsDto tickets, int manualCount) {
        System.out.printf("\n수동으로 %d장, 자동으로 %d장을 구매했습니다.\n", manualCount, tickets.size() - manualCount);
        for (List<Integer> ticket : tickets.getTickets()) {
            printLottoTicket(ticket);
        }
        System.out.println();
    }

    private void printLottoTicket(List<Integer> ticket) {
        String numbers = ticket.stream()
                .sorted().map(
                        number -> Integer.toString(number)
                ).collect(Collectors.joining(", "));
        System.out.println("[" + numbers + "]");
    }

    public void printResultStatistics(Result result, int money) {
        String format = "\n당첨 통계\n" +
                "--------\n" +
                "3개 일치 (5000원) - %d개\n" +
                "4개 일치 (50000원) - %d개\n" +
                "5개 일치 (1500000원) - %d개\n" +
                "5개 일치, 보너스 볼 일치(30000000원) - %d개\n" +
                "6개 일치 (2000000000원) - %d개\n" +
                "총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)\n";
        System.out.printf(
                format,
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
