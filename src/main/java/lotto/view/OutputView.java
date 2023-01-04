package lotto.view;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.RankingResult;
import lotto.model.enums.RankingType;

public class OutputView {

    public void printNumberOfLottos(Integer numberOfManualLotto, Integer numberOfAutoLotto){
        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.%n", numberOfManualLotto, numberOfAutoLotto);
    }

    public void printLottos(Lottos lottos, int numberOfManualLotto, int numberOfAutoLotto) {
        printNumberOfLottos(numberOfManualLotto, numberOfAutoLotto);
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getLotto());
        }
    }

    public void printStatistic(RankingResult rankingResult){
        System.out.println("\n당첨 통계\n" +
                "---------");
        for (int i = RankingType.values().length -2; i >= 0  ; i--){
            System.out.print(RankingType.values()[i].getHowManyMatches());
            System.out.printf(" - %d개%n", rankingResult.getRankingResult().get(RankingType.values()[i]));
        }
    }

    public void printRevenue(double revenue){
        System.out.printf("총 수익률은 %.2f입니다.%n", revenue);
    }
}
