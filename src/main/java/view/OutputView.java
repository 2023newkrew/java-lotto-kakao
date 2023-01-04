package view;

import domain.LottoNumbers;

import java.util.stream.Collectors;

public class OutputView {

    private static final String RESULT_STATISTICS_MESSAGE = "\n당첨 통계\n---------";
    private static final String YIELD_MESSAGE = "총 수익률은 %.2f입니다.";

    public void printPurchasedLotto(PurchasedLotto purchasedLotto) {
        System.out.println(purchasedLotto.toString());
        String lottoNumbers = purchasedLotto.getLottoNumbersList()
                .stream()
                .map(LottoNumbers::toString)
                .collect(Collectors.joining("\n"));

        System.out.println(lottoNumbers);
    }

    public void printStatistics(String str) {
        System.out.println(RESULT_STATISTICS_MESSAGE);
        System.out.println(str);
    }

    public void printYield(double yield) {
        System.out.println(String.format(YIELD_MESSAGE, yield));
    }

}
