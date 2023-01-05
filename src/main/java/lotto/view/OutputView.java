package lotto.view;

import lotto.model.LottoCount;
import lotto.model.LottoList;
import lotto.model.LottoResult;
import lotto.model.LottoStatistics;

import java.util.Arrays;

public class OutputView {

    public void printLottoCount(LottoCount lottoCount, Integer manualLottoCount) {
        System.out.println();
        System.out.println("수동으로 " + manualLottoCount + "장, 자동으로 " + lottoCount.get() + "개를 구매했습니다.");
    }

    public void printLottoList(LottoList lottoList) {
        System.out.println(lottoList);
        System.out.println();
    }

    public void printLottoStatistics(LottoStatistics lottoStatistics) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        Arrays.stream(LottoResult.values())
                .filter(lottoResult -> lottoResult != LottoResult.LOSE)
                .forEach(lottoResult ->
                        System.out.printf("%s - %d개\n", lottoResult, lottoStatistics.get(lottoResult))
                );
    }

    public void printRateOfProfit(LottoStatistics lottoStatistics, Integer inputPrice) {
        System.out.printf("총 수익률은 %.2f입니다.\n", lottoStatistics.getProfit() / Double.valueOf(inputPrice));
    }

}
