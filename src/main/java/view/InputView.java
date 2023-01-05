package view;

import java.util.Scanner;

public class InputView {
    public String getMoneyInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("구매금액을 입력해 주세요.");
        return scanner.next();
    }

    public String getWinningLottoInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public String getBonusNumberInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.next();
    }
}
