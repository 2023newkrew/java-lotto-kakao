package view;

import domain.lotto.number.LottoNumbers;
import domain.lotto.result.LottoResultType;
import domain.lotto.result.LottoResults;
import domain.lotto.number.LottoTickets;

import java.util.Arrays;

public class OutputView {

    public static void printLottoTickets(final LottoTickets lottoTickets) {
        System.out.println(lottoTickets.getLottoTickets().size() + "개를 구매했습니다.");
        for (LottoNumbers lottoNumbers : lottoTickets.getLottoTickets()) {
            System.out.println(lottoNumbers.toString());
        }
    }

    public static void printLottoResults(final LottoResults lottoResults) {
        System.out.println("당첨 통계\n" + "---------");
        Arrays.stream(LottoResultType.values())
                .forEach((resultType) -> {
                    if (LottoResultType.FAIL.equals(resultType)) return;
                    System.out.println(resultType.getMessage() + " - " + lottoResults.getResultCount(resultType) + "개");
                });
        System.out.printf("총 수익률은 %.2f입니다.(기준점은 1)\n", lottoResults.getProfitRate());
    }

    public static void printErrorMessage(final String errorMessage) {
        System.out.println("️⚠️"+errorMessage);
    }
}
