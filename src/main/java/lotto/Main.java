package lotto;

public class Main {

    public static void main(String[] args) {
        MainUI mainUI = new MainUI(System.in, System.out);
        mainUI.initPurchasePrice();
        mainUI.printTickets();
        mainUI.initWinningNumber();
        mainUI.run();
    }
}
