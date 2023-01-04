package view;

import domain.Lottos;

import java.util.List;

public class OutputView {

    public static final String FIRST_PLACE_DESCRIPTON = "6개 일치";
    public static final String SECOND_PLACE_DESCRIPTON = "5개 일치, 보너스 볼 일치";
    public static final String THIRD_PLACE_DESCRIPTON = "5개 일치";
    public static final String FOURTH_PLACE_DESCRIPTON = "4개 일치";
    public static final String FIFTH_PLACE_DESCRIPTON = "3개 일치";
    public static final String NONE_DESCRIPTON = "";
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

    public static void printTotalResult(List<String> lottosNumbers) {
        lottosNumbers.stream().forEach(System.out::println);
        System.out.println();
    }

    public static void printProfit(String profitMessage) {
        System.out.println(profitMessage);
    }

}
