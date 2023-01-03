package lotto.view;

import java.util.Scanner;

public class InputView {
    private static InputView instance;
    private final Scanner sc;

    private InputView() {
        sc = new Scanner(System.in);
    }

    public static InputView getInstance() {
        if (instance == null) {
            instance = new InputView();
        }
        return instance;
    }

    public int getMoneyInput() {
        System.out.println("구입금액을 입력해 주세요.");
        String money;
        do {
            money = sc.nextLine();
        } while (validateMoneyInput(money)) ;
        return Integer.parseInt(money);
    }

    private boolean validateMoneyInput(String moneyStr) {
        try {
            int money = Integer.parseInt(moneyStr);
            return validateMoneyRange(money);
        } catch (Exception e) {
            System.out.println("금액은 숫자여야합니다.");
            return true;
        }
    }

    private boolean validateMoneyRange(int money) {
        if (money < 0) {
            System.out.println("금액은 0보다 크거나 같은 숫자여야 합니다");
            return true;
        }
        return false;
    }

    public String getWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return sc.nextLine();
    }

    public String getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return sc.nextLine();
    }
}
