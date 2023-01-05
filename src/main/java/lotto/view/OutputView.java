package lotto.view;

import lotto.model.Lottos;
import lotto.model.PriceResult;

import java.util.stream.Collectors;

public class OutputView {

    private static final String MSG_PURCHASE_LOTTO_COUNT_FORMAT = "%s개를 구매했습니다.";
    private static final String MSG_GENERATED_LOTTO_NUMBERS_FORMAT = "[%s]\n";
    private static final String MSG_PRICE_RESULT_FORMAT = "%s (%s)- %s개\n";
    private static final String MSG_EARNING_RATE_FORMAT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 ";
    private static final String GAIN = "이득";
    private static final String PRINCIPAL = "본전";
    private static final String LOSS = "손해";
    private static final String MSG_RESULT_FORMAT = "결과적으로 %s라는 의미임)\n";
    private static final String NUMBERS_DELIMITER = ", ";

    public static void printLottoCount(int size) {
        System.out.printf(MSG_PURCHASE_LOTTO_COUNT_FORMAT, size);
    }

    public static void printLottos(Lottos lottos) {
        lottos.getLottos().forEach(v ->
                System.out.printf(MSG_GENERATED_LOTTO_NUMBERS_FORMAT, v.getNumbers().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(NUMBERS_DELIMITER))));
    }

    public static void printResult(PriceResult result) {
        result.getResult().forEach((k, v) -> System.out.printf(MSG_PRICE_RESULT_FORMAT, k.matchDescription(), k.price(), v));
    }

    public static void printEarningRate(double earningsRate) {
        System.out.printf(MSG_EARNING_RATE_FORMAT, earningsRate);
        String result = LOSS;
        if (earningsRate > 1) {
            result = GAIN;
        }
        if (earningsRate == 1) {
            result = PRINCIPAL;
        }
        System.out.printf(MSG_RESULT_FORMAT, result);
    }
}
