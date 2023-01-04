package view;

import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    public static String getPaidPrice() {
        return scanner.nextLine();
    }

    public static String getWinningLottoNumbers() {
        return scanner.nextLine();
    }

    public static String getBonusNumber() {
        return scanner.nextLine();
    }

}
