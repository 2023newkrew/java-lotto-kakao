package lotto.view;

import java.util.Scanner;

public class InputView {
    private static Scanner sc = new Scanner(System.in);

    public static int scanPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int purchaseAmount = sc.nextInt();

        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException("로또 가격은 1000원입니다. 1000의 배수를 입력해야 합니다.");
        }
        return purchaseAmount;
    }

    public static String scanWinningNumberString() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningNumberString = sc.next();
        return winningNumberString;
    }

    public static int scanBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = sc.nextInt();
        return bonusNumber;
    }
}
