package lotto.view;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.PrizeGroupingMap;

public class OutputView {

    public void printPurchaseResult(int manualLottoCount, int autoLottoCount) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.%n", manualLottoCount, autoLottoCount);
    }

    public void printManualLottoGuide() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public void printUserLottos(List<Lotto> lottoList) {
        lottoList.forEach(System.out::println);
    }

    public void printResult(PrizeGroupingMap result) {
        System.out.println("당첨 통계");
        System.out.println("----------");
        System.out.println(result);
    }
}
