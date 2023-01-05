package lotto.view;

import java.util.Scanner;

public class InputView {
    private Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int scanPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int purchaseAmount = scanner.nextInt();
        return purchaseAmount;
    }

    public String scanWinningNumberString() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningNumberString = scanner.next();
        return winningNumberString;
    }

    public int scanBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = scanner.nextInt();
        return bonusNumber;
    }
}
