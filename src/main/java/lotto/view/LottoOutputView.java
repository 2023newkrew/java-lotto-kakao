package lotto.view;

import lotto.model.lotto.Lotto;
import lotto.model.prize.Prize;
import lotto.model.prize.PrizeMap;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class LottoOutputView {
    public void printLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            printLotto(lotto);
        }
        System.out.println();
    }

    private void printLotto(Lotto lotto) {
        String lottoString = lotto.getLottoNumbers()
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(lottoString);
    }

    public void printWinningStatistics(PrizeMap prizeMap, BigDecimal profitRate){
        printWinningStatisticsHeader();
        printWinningPrizes(prizeMap);
        printProfitRate(profitRate);
    }

    private static void printWinningStatisticsHeader() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private static void printWinningPrizes(PrizeMap prizeMap) {
        System.out.printf("3개 일치 (5000원)- %d개%n", prizeMap.countBy(Prize.FIFTH));
        System.out.printf("4개 일치 (50000원)- %d개%n", prizeMap.countBy(Prize.FOURTH));
        System.out.printf("5개 일치 (150000원)- %d개%n", prizeMap.countBy(Prize.THIRD));
        System.out.printf("5개 일치, 보너스 볼 일치(30000000원)- %d개%n", prizeMap.countBy(Prize.SECOND));
        System.out.printf("6개 일치 (2000000000원)- %d개%n", prizeMap.countBy(Prize.FIRST));
    }

    private static void printProfitRate(BigDecimal profitRate) {
        System.out.printf("총 수익율은 %.2f입니다.", profitRate.doubleValue());
    }
}
