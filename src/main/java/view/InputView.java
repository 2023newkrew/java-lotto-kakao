package view;

import java.util.Scanner;

import static utils.InputMessage.*;

public class InputView {
    public String getMoneyInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(INPUT_MONEY.getMessage());
        return scanner.next();
    }

    public String getWinningLottoInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(INPUT_WINNING_LOTTO_NUMBERS.getMessage());
        return scanner.nextLine();
    }

    public String getBonusNumberInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(INPUT_BONUS_NUMBER.getMessage());
        return scanner.next();
    }
}
