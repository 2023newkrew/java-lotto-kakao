package lotto.view;

public class ResultView {

    public static void print(String message) {
        System.out.println(message);
    }

    public static void printQuantity(int ticketQuantity) {
        print(String.format("%d개를 구매했습니다.", ticketQuantity));
    }
}
