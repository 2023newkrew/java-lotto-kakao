package lotto.view;

import lotto.model.Grade;
import lotto.model.LottoTicket;
import lotto.model.LottoTickets;
import lotto.model.Result;

import java.util.List;
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

    public void printLottoTickets(LottoTickets tickets) {
        System.out.printf("%d개 구매했습니다.\n", tickets.getTicket().size());
        for (LottoTicket ticket : tickets.getTicket()) {
            List<String> numbers = ticket.toIntegerList()
                    .stream().map(
                            number -> Integer.toString(number)
                    ).collect(Collectors.toList());
            System.out.println("[" + String.join(", ", numbers) + "]");
        }
        System.out.println();
    }

    public void printResultStatistics(Result result, int money) {
        System.out.printf("당첨 통계\n" +
                        "--------\n" +
                        "3개 일치 (5000원) - %d개\n" +
                        "4개 일치 (50000원) - %d개\n" +
                        "5개 일치 (1500000원) - %d개\n" +
                        "5개 일치, 보너스 볼 일치(30000000원) - %d개\n" +
                        "6개 일치 (2000000000원) - %d개\n" +
                        "총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)\n",
                result.get(Grade.THREE),
                result.get(Grade.FOUR),
                result.get(Grade.FIVE),
                result.get(Grade.FIVE_BONUS),
                result.get(Grade.SIX),
                result.getProfitRate(money),
                result.getProfitRate(money) > 1 ? "이익" : "손해");
    }
}
