package javalotto.view;

import javalotto.domain.Lotto;
import javalotto.domain.LottoCount;
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

        return PurchaseAmount.from(Integer.parseInt(scanner.nextLine()));
    }

    public WinningLotto getWinningLottoInput() {
        List<Integer> winningNumbers = getWinningNumbersInput();
        int bonusNumber = getBonusNumberInput();

        return WinningLotto.of(Lotto.from(winningNumbers), bonusNumber);
    }

    public LottoCount getManualPurchaseQuantityInput() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        int purchaseQuantity = Integer.parseInt(scanner.nextLine());
        return LottoCount.withCount(purchaseQuantity);
    }

    private List<Integer> getWinningNumbersInput() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String inputNumbers = scanner.nextLine();

        return Arrays.stream(inputNumbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private int getBonusNumberInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }
}
