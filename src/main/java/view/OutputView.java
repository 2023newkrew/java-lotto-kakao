package view;

import java.util.List;

public class OutputView {
    public static void printPaidPriceRequest() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printLottoCount(int manualLottoCount, int automaticLottoCount) {
        System.out.println(String.format("수동으로 %d장, 자동으로%d장을 구매했습니다.", manualLottoCount, automaticLottoCount));
    }

    public static void printLottos(List<String> lottosNumbers) {
        lottosNumbers.stream()
                .forEach(System.out::println);
        System.out.println();
    }

    public static void printManualLottoCountRequest() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public static void printManualLottosNumbersRequest() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public static void printWinningNumbersRequest() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printBonusNumberRequest() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void printTotalResult(List<String> lottosNumbers) {
        lottosNumbers.stream()
                .forEach(System.out::println);
        System.out.println();
    }

    public static void printProfit(String profitMessage) {
        System.out.println(profitMessage);
    }


}
