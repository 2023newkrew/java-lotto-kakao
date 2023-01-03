package lotto.view;

import lotto.model.Grade;
import lotto.model.LottoTicket;
import lotto.model.LottoTickets;
import lotto.model.Result;

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

    public void printCountOfLottoTickets(int count) {
        System.out.printf("%d개 구매했습니다.\n", count);
    }

    public void printLottoTickets(LottoTickets tickets) {
        for (LottoTicket ticket : tickets.getTicket()) {
            System.out.println(ticket);
        }
    }

    public void printResultStatistics(Result result, int money) {
        System.out.println("당첨 통계");
        System.out.println("--------");
        System.out.println("3개 일치 (5000원) - " + result.get(Grade.THREE) + "개");
        System.out.println("4개 일치 (50000원) - " + result.get(Grade.FOUR) + "개");
        System.out.println("5개 일치 (1500000원) - " + result.get(Grade.FIVE) + "개");
        System.out.println("5개 일치, 보너스 볼 일치(30000000원) - " + result.get(Grade.FIVE_BONUS) + "개");
        System.out.println("6개 일치 (2000000000원) - " + result.get(Grade.SIX) + "개");

        //수익률 출력
        float rate = (float) (
                result.get(Grade.THREE) * 5000 + result.get(Grade.FOUR) * 50000 + result.get(Grade.FIVE) * 1500000 + result.get(Grade.FIVE_BONUS) * 300000000 + result.get(Grade.SIX)
        ) / (float) money;
        System.out.printf("총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)", rate, rate > 1 ? "이익" : "손해");
    }
}
