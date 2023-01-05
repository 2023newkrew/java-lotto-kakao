package lotto.view;

import lotto.domain.LottoPrizeCountMap;
import lotto.domain.UserLotto;

import java.util.List;

public class OutputView {

    public void printPurchaseAmount(int totalAmount, int manualAmount) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.%n", manualAmount, totalAmount - manualAmount);
    }

    public void printUserLottos(List<UserLotto> userLottos) {
        userLottos.forEach(System.out::println);
    }

    public void printResult(LottoPrizeCountMap result) {
        System.out.println("당첨 통계");
        System.out.println("----------");
        System.out.println(result);
    }
}
