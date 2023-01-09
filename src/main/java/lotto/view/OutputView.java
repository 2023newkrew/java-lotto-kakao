package lotto.view;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Ranking;

import java.util.Map;

/**
 * 모델의 정보를 컨트롤러에게서 넘겨받아 출력하는 클래스입니다.
 */
public class OutputView {

    public void printLottos(Lottos lottos, int numberOfManualLottos) {
        int numberOfAutomatedLottos = lottos.getLottos().size() - numberOfManualLottos;
        System.out.println(String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.", numberOfManualLottos, numberOfAutomatedLottos));
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getLotto());
        }
        System.out.println();
    }

    public void printStatistic(Map<Ranking, Integer> rankingResult) {
        System.out.println("당첨 통계\n" +
                "---------");
        for (int i = Ranking.values().length - 2; i >= 0; i--) {
            System.out.print(Ranking.values()[i].getResultLog());
            System.out.println(String.format("- %d개", rankingResult.get(Ranking.values()[i])));
        }
    }

    public void printRevenue(double revenue) {
        System.out.println(String.format("총 수익률은 %.2f입니다.", revenue));
    }
}
