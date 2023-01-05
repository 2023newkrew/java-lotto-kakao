package view;

import domain.lotto.result.LottoResultType;
import domain.lotto.result.LottoResults;
import domain.lotto.ticket.LottoTicket;
import domain.lotto.ticket.LottoTicketList;

import java.util.Arrays;

public class OutputView {

    public static void printLottoTickets(LottoTicketList lottoTicketList) {
//        System.out.println(lottoTicketList.getLottoTickets().size() + "개를 구매했습니다.");
        for (LottoTicket lottoTicket : lottoTicketList.getLottoTickets()) {
            System.out.println(lottoTicket.toString());
        }
    }

    public static void printManualAndAutoCount(int manual, int auto) {
        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manual, auto);

    }

    public static void printLottoResults(LottoResults lottoResults) {
        System.out.println("당첨 통계\n" + "---------");
        Arrays.stream(LottoResultType.values())
                .forEach((resultType) -> {
                    if (LottoResultType.FAIL.equals(resultType)) return;
                    System.out.println(resultType.getMessage() + " - " + lottoResults.getResultCount(resultType) + "개");
                });

        printProfitRate(lottoResults.getProfitRate());
    }

    private static void printProfitRate(float profitRate) {
        if (profitRate < 1.0) {
            System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)\n", profitRate);
            return;
        }

        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 이익이라는 의미임)\n", profitRate);
    }
}
