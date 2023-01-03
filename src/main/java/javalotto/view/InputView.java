package javalotto.view;

import javalotto.domain.Lotto;
import javalotto.domain.PurchaseAmount;
import javalotto.domain.WinningLotto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private Scanner scanner;

    private InputView() {
        this.scanner = new Scanner(System.in);
    }

    public static InputView newInstance() {
        return new InputView();
    }

    public PurchaseAmount getPurchaseAmountInput() {
        System.out.println("구입금액을 입력해 주세요.");

        return PurchaseAmount.from(scanner.nextInt());
    }

    public WinningLotto getWinningLottoInput() {
        List<Integer> winningNumbers = getWinningNumbersInput();
        int bonusNumber = getBonusNumberInput();

        return WinningLotto.of(Lotto.from(winningNumbers), bonusNumber);
    }

    private List<Integer> getWinningNumbersInput() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String inputNumbers = scanner.nextLine();

        return Arrays.stream(inputNumbers.split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private int getBonusNumberInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }
}
