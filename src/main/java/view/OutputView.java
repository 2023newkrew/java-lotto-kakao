package view;

import domain.lotto.number.LottoNumbers;
import domain.lotto.result.LottoResultType;
import domain.lotto.result.LottoResults;
import domain.lotto.number.LottoTickets;

import java.util.Arrays;

public class OutputView {

    public static void printLottoTickets(final LottoTickets lottoTickets) {
        for (LottoNumbers lottoNumbers : lottoTickets.getLottoTickets()) {
            System.out.println(lottoNumbers.toString());
        }
    }

    public static void printLottoResults(final LottoResults lottoResults) {
        System.out.println("\n당첨 통계\n" + "---------");
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

    public static void printStartInputManualLottoNumbersMessage() {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
    }

    public static void printAutoAmountAndManualAmount(final int autoAmount, final int manualAmount) {
        System.out.println(String.format("\n수동으로 %d 장, 자동으로 %d 장을 구매했습니다.", manualAmount, autoAmount));
    }
}
