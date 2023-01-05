package lotto.view;

import java.util.Scanner;

import static lotto.utils.LottoMessage.*;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public int inputUserAmount(){
        System.out.println(INPUT_AMOUNT.getMessage());
        return Integer.parseInt(scanner.nextLine());
    }

    public String inputWinNumber(){
        System.out.println(INPUT_WIN_NUMBERS.getMessage());
        return scanner.nextLine();
    }

    public int inputBonusNumber(){
        System.out.println(INPUT_BONUS_BALL.getMessage());
        return Integer.parseInt(scanner.nextLine());
    }
}
