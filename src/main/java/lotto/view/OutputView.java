package lotto.view;

import lotto.model.Lottos;
import lotto.model.PriceResult;

import java.util.stream.Collectors;

public class OutputView {
    public static void printLottos(Lottos lottos) {
        lottos.getLottos().forEach(v ->
                System.out.printf("[%s]\n", v.getNumbers().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", "))));
    }

    public static void printResult(PriceResult result) {
        result.getResult().forEach((k, v) -> System.out.printf("%s (%s)- %s개\n", k.matchDescription(), k.price(), v));
    }

    public static void printEarningRate(double earningsRate) {
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 ", earningsRate);
        if (earningsRate > 1) {
            System.out.println("결과적으로 이득이라는 의미임)");
        }
        if (earningsRate == 1) {
            System.out.println("결과적으로 본전이라는 의미임");
        }
        if (earningsRate < 1) {
            System.out.println("결과적으로 손해라는 의미임)");
        }
    }
}
