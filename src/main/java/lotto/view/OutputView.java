package lotto.view;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.PrizeCountMap;

public class OutputView {

    public void printPurchaseResult(int amount) {
        System.out.printf("%d개를 구매했습니다.%n", amount);
    }

    public void printUserLottos(List<Lotto> lottoList) {
        lottoList.forEach(System.out::println);
    }

    public void printResult(PrizeCountMap result) {
        System.out.println("당첨 통계");
        System.out.println("----------");
        System.out.println(result);
    }
}
