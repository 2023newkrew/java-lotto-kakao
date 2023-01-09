package view;

import common.state.Result;
import domain.Lottos;
import domain.TotalResult;

import java.util.Arrays;
import java.util.List;

public class OutputView {

    public static final String FIRST_PLACE_DESCRIPTION = "6개 일치";
    public static final String SECOND_PLACE_DESCRIPTION = "5개 일치, 보너스 볼 일치";
    public static final String THIRD_PLACE_DESCRIPTION = "5개 일치";
    public static final String FOURTH_PLACE_DESCRIPTION = "4개 일치";
    public static final String FIFTH_PLACE_DESCRIPTION = "3개 일치";
    public static final String NONE_DESCRIPTION = "";
    public static final String TOTAL_RESULT_MESSAGE = "%s (%d원)- %d개";
    public static final String PROFIT_MESSAGE = "총 수익률은 %.2f입니다.";
    public static final String PROFIT_NEGATIVE_MESSAGE = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    public static final String PROFIT_POSITIVE_MESSAGE = "(기준이 1이기 때문에 결과적으로 이득라는 의미임)";


    public static void printPaidPriceRequest() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printManualLottoCountRequest() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public static void printManualLottoRequest() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public static void printLottoCount(int manualCount, int autoCount) {
        System.out.println(String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.", manualCount, autoCount));
    }

    public static void printLottos(List<String> lottosNumbers) {
        lottosNumbers.stream().forEach(System.out::println);
        System.out.println();
    }

    public static void printWinningNumbersRequest() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printBonusNumberRequest() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void printTotalResult(TotalResult totalResult) {
        Arrays.stream(Result.values())
                .filter(result -> result != Result.NONE)
                .map(result -> String.format(TOTAL_RESULT_MESSAGE,
                        result.getDescription(),
                        result.getWinnings(),
                        totalResult.getValueOfResult(result)))
                .forEach(System.out::println);
        System.out.println();
    }

    public static void printProfit(double profit) {
        String message = String.format(PROFIT_MESSAGE, profit);
        if (profit <= 1) {
            System.out.println(message + PROFIT_NEGATIVE_MESSAGE);
            return;
        }
        System.out.println(message + PROFIT_POSITIVE_MESSAGE);
    }

}
