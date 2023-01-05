package view;

import java.util.Scanner;

import static utils.InputMessage.*;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static String getMoneyInput() {
        System.out.println(INPUT_MONEY.getMessage());
        return scanner.nextLine();
    }

    public static String getManualLottoAmountInput() {
        System.out.println(INPUT_MANUAL_LOTTO_AMOUT.getMessage());
        return scanner.nextLine();
    }

    public static String getManualLottoNumbersInput() {
        return scanner.nextLine();
    }

    public static String getWinningLottoInput() {
        System.out.println(INPUT_WINNING_LOTTO_NUMBERS.getMessage());
        return scanner.nextLine();
    }

    public static String getBonusNumberInput() {
        System.out.println(INPUT_BONUS_NUMBER.getMessage());
        return scanner.nextLine();
    }
}
