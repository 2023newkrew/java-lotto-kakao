package lotto.view;

import lotto.model.LottoList;
import lotto.model.LottoResult;
import lotto.model.LottoStatistics;

import java.util.Arrays;

public class OutputView {

    public void printLottoCount(Integer lottoCount) {
        System.out.println(lottoCount + "개를 구매했습니다.");
    }

    public void printLottoList(LottoList lottoList) {
        System.out.println(lottoList);
    }

    public void printLottoStatistics(LottoStatistics lottoStatistics) {
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
