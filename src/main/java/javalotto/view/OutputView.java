package javalotto.view;


import javalotto.domain.*;

public class OutputView {

    public void printLottoCount(LottoCount lottoCount) {
        System.out.println(lottoCount.getCount() + "개를 구매했습니다.");
    }

    public void printLottos(Lottos lottos) {
        System.out.println(lottos);
    }

    public void printResult(WinningLotto winningLotto, Lottos lottos) {
        LottoResult lottoResult = lottos.getLottoResult(winningLotto);
        printWinningStatistics(lottoResult);
    }

    public void printWinningStatistics(LottoResult lottoResult) {
        System.out.println("당첨 통계");
        System.out.println("--------");
        System.out.println(lottoResult);
    }

    public void printRateOfReturn(LottoResult lottoResult, PurchaseAmount purchaseAmount) {
        Double totalRateOfReturn = lottoResult.getTotalRateOfReturn(purchaseAmount);
        String rateOfReturnMeaningMessage = getRateOfReturnMeaningMessage(totalRateOfReturn);
        System.out.printf("총 수익률은 %.2f 입니다. %s%n", totalRateOfReturn, rateOfReturnMeaningMessage);
    }

    private static String getRateOfReturnMeaningMessage(Double totalRateOfReturn) {
        if (totalRateOfReturn > 1) {
            return "(기준이 1이기 때문에 결과적으로 이득이라는 의미임)";
        }
        if (totalRateOfReturn == 1) {
            return "(기준이 1이기 때문에 결과적으로 구입금액과 수익이 일치한다는 의미임)";
        }
        if (totalRateOfReturn < 1) {
            return "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
        }
        return "";
    }
}
