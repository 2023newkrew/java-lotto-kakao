package view;

import domain.lotto.result.LottoResultType;
import domain.lotto.result.LottoResults;
import domain.lotto.ticket.LottoTicket;
import domain.lotto.ticket.LottoTickets;

import java.util.Arrays;
import java.util.List;

public class OutputView {

    public static void printLottoTickets(final LottoTickets lottoTickets) {
        System.out.println(lottoTickets.getLottoTickets().size() + "개를 구매했습니다.");
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            System.out.println(lottoTicket.toString());
        }
    }

    public static void printLottoResults(final LottoResults lottoResults) {
        System.out.println("당첨 통계\n" + "---------");
        Arrays.stream(LottoResultType.values())
                .forEach((resultType) -> {
                    if (LottoResultType.FAIL.equals(resultType)) return;
                    System.out.println(resultType.getMessage() + " - " + lottoResults.getResultCount(resultType) + "개");
                });
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)\n", lottoResults.getProfitRate());
    }
}
