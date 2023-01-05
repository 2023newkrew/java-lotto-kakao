package lotto.view;

import lotto.domain.LottoPrizeCountMap;
import lotto.domain.UserLotto;

import java.util.List;

public class OutputView {

    public void printPurchaseResult(int amount) {
        System.out.printf("%d개를 구매했습니다.%n", amount);
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
