package javalotto.view;


import javalotto.domain.*;

public class OutputView {

    private OutputView() {}

    public static OutputView newInstance() {
        return new OutputView();
    }

    public void printLottos(LottoBuyer lottoBuyer, Lottos lottos) {
        System.out.println(String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.",
                lottoBuyer.getManualLottoCount(), lottoBuyer.getAutoLottoCount()));
        System.out.println(lottos);
    }

    public void printLottoResult(LottoBuyer lottoBuyer, LottoResult lottoResult) {
        printWinningStatistics(lottoResult);
        printRateOfReturn(lottoBuyer, lottoResult);
    }

    private void printWinningStatistics(LottoResult lottoResult) {
        System.out.println("당첨 통계");
        System.out.println("--------");
        System.out.println(lottoResult);
    }

    private void printRateOfReturn(LottoBuyer lottoBuyer, LottoResult lottoResult) {
        double rateOfReturn = lottoBuyer.getRateOfReturn(lottoResult);
        System.out.println(String.format("총 수익률은 %.2f입니다.%s", rateOfReturn, rateOfReturnDescription(rateOfReturn)));
    }

    private String rateOfReturnDescription(double rateOfReturn) {
        if (rateOfReturn < 1.0d) {
            return "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
        }
        if (rateOfReturn == 1.0d) {
            return "(기준이 1이기 때문에 결과적으로 구입금액과 수익이 일치한다는 의미임)";
        }
        return "(기준이 1이기 때문에 결과적으로 이득이라는 의미임)";
    }
}
