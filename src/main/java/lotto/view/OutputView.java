package lotto.view;

import lotto.model.LottoList;
import lotto.model.LottoStatistics;
import lotto.model.enums.LottoResult;

import java.util.Arrays;
import java.util.Comparator;

public class OutputView {

    public void printLottoCount(Integer manualLottoCount, Integer automaticLottoCount) {
        System.out.printf("수동으로 %d장, 자동으로 %d장을 구매했습니다.\n", manualLottoCount, automaticLottoCount);
    }

    public void printRequestManualLottoNumber() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public void printLottoList(LottoList lottoList) {
        System.out.println(lottoList);
    }

    public void printLottoStatistics(LottoStatistics lottoStatistics) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        Arrays.stream(LottoResult.values())
                .takeWhile(lottoResult -> lottoResult != LottoResult.LOSE)
                .sorted(Comparator.reverseOrder())
                .forEach(lottoResult ->
                        System.out.printf("%s - %d개\n", lottoResult, lottoStatistics.get(lottoResult))
                );
    }

    public void printRateOfProfit(LottoStatistics lottoStatistics, Integer inputPrice) {
        System.out.printf("총 수익률은 %.2f입니다.\n", lottoStatistics.getRateOfProfit(inputPrice));
    }

}
