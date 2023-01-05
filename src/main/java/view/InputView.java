package view;

import java.util.Scanner;

public class InputView {
    private static final String NOTICE_MONEY_INPUT = "구매금액을 입력해 주세요.";
    private static final String NOTICE_WINNING_NUMBERS_INPUT = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String NOTICE_BONUS_NUMBER_INPUT = "보너스 볼을 입력해 주세요.";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public void close() {
        scanner.close();
    }

    public String getMoneyInput() {
        System.out.println(NOTICE_MONEY_INPUT);
        String input =  scanner.next();
        scanner.nextLine();
        return input;
    }

    public String getWinningLottoInput() {
        System.out.println(NOTICE_WINNING_NUMBERS_INPUT);
        return scanner.nextLine();
    }

    public String getBonusNumberInput() {
        System.out.println(NOTICE_BONUS_NUMBER_INPUT);
        String input =  scanner.next();
        scanner.nextLine();
        return input;
    }
}
