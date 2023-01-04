package lotto.view;

import java.util.Scanner;

public class LottoInputTemplate {
    private final Scanner scanner;

    public LottoInputTemplate() {
        scanner = new Scanner(System.in);
    }

    private String getNextInputLine() {
        return scanner.nextLine().trim();
    }

    public String inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return getNextInputLine();
    }

    public String inputLottoWinningNumbers() {
        System.out.println("지난 주 당첨 번호 입력해 주세요.");
        return getNextInputLine();
    }

    public String inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return getNextInputLine();
    }
}
