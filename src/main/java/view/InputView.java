package view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int getBudgetInput() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    public int getManualLottoQuantityInput() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextInt();
    }

    public String getManualNumbersInput() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        scanner.nextLine();
        return scanner.nextLine();
    }

    public String getWinningNumbersInput() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        scanner.nextLine();
        return scanner.nextLine();
    }

    public int getBonusNumberInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }
}
