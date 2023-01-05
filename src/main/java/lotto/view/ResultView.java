package lotto.view;

public class ResultView {

    public static void print(String message) {
        System.out.println(message);
    }

    public static void printQuantity(int manual, int random) {
        print(String.format("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.", manual, random));
    }

    public static void printStatistics(String winningString) {
        print("\n당첨 통계");
        print("---------");
        print(winningString);
    }
}
