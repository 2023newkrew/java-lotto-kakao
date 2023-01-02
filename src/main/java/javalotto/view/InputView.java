package javalotto.view;

import javalotto.domain.PurchaseAmount;

import java.util.Scanner;

public class InputView {
    private Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public PurchaseAmount getPurchaseAmountInput() {
        System.out.println("구입금액을 입력해 주세요.");

        return PurchaseAmount.from(scanner.nextInt());
    }
}
