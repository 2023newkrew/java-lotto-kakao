package lotto.view;

import java.util.*;
import java.util.stream.Collectors;
import lotto.domain.WinningLotto;

import static lotto.view.LottoPrinter.*;

public class LottoView {
    private static final Scanner scanner = new Scanner(System.in);

    private static List<Integer> inputLottoNumbers() {
        return Arrays.stream(scanner.nextLine().trim().replace(" ", "").split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int inputTotalMoney() {
        printTotalMoneyMessage();
        return Integer.parseInt(scanner.nextLine());
    }

    private static List<Integer> inputWinningLottoNumbers() {
        printWinningLottoNumbersMessage();
        return inputLottoNumbers();
    }

    private static int inputBonusNumber() {
        printBonusNumberMessage();
        return scanner.nextInt();
    }

    public static WinningLotto inputWinningLotto() {
        return new WinningLotto(inputWinningLottoNumbers(), inputBonusNumber());
    }

    public static int inputManualLottoAmount() {
        printManualLottoAmountMessage();
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<List<Integer>> inputManualLottoNumbers(int manualLottoAmount) {
        List<List<Integer>> lottoNumbersList = new ArrayList<>();

        printManualLottoNumbersMessage();
        for (int i = 0; i < manualLottoAmount; i++)
            lottoNumbersList.add(inputLottoNumbers());
        return lottoNumbersList;
    }
}
