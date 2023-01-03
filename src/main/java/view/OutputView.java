package view;

import domain.Lottos;

import java.util.List;

public class OutputView {
    public static void printPaidPriceRequest() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printLottoCount(int count) {
        System.out.println(String.format("%d개를 구매했습니다.", count));
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
