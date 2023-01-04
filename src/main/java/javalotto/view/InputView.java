package javalotto.view;

import javalotto.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private final Scanner scanner;

    private InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public static InputView create() {
        return new InputView(new Scanner(System.in));
    }

    public PurchaseAmount getPurchaseAmountInput() {
        System.out.println("구입금액을 입력해 주세요.");

        return PurchaseAmount.from(Integer.parseInt(scanner.nextLine()));
    }

    public LottoCount getManuallyLottoCountInput() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        return LottoCount.withCount(Integer.parseInt(scanner.nextLine()));
    }

    public Lottos getManuallyLottosInput(LottoCount manuallyLottoCount) {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        List<Lotto> manuallyPurchasedLottos = new ArrayList<>();
        for (int i = 0; i < manuallyLottoCount.getCount(); i++) {
            List<Integer> lottoNumbers = getNumbersInput();
            manuallyPurchasedLottos.add(Lotto.from(lottoNumbers));
        }
        return Lottos.from(manuallyPurchasedLottos);
    }

    public WinningLotto getWinningLottoInput() {
        List<Integer> winningNumbers = getWinningNumbersInput();
        int bonusNumber = getBonusNumberInput();

        return WinningLotto.of(Lotto.from(winningNumbers), bonusNumber);
    }

    private List<Integer> getWinningNumbersInput() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return getNumbersInput();
    }

    private List<Integer> getNumbersInput() {
        String inputNumbers = scanner.nextLine();
        return getNumberListBy(inputNumbers);
    }

    private static List<Integer> getNumberListBy(String inputNumbers) {
        return Arrays.stream(inputNumbers.split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private int getBonusNumberInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }
}
