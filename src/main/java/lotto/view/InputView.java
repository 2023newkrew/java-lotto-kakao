package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public int scanNumberOfManualLotto() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int numberOfManualLotto = scanner.nextInt();
        return numberOfManualLotto;
    }

    public List<List<Integer>> scanManualLottoNumbers(int numberOfManualLotto) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        return IntStream.range(0, numberOfManualLotto)
                .mapToObj(__ -> parseIntsWithDelimiter(scanner.next(), ","))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Integer> scanWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningNumberString = scanner.next();
        return parseIntsWithDelimiter(winningNumberString, ",");
    }

    public int scanBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = scanner.nextInt();
        return bonusNumber;
    }

    private List<Integer> parseIntsWithDelimiter(String input, String delimiter) {
        return Arrays.stream(input.split(delimiter))
                .map(Integer::parseInt)
                .collect(Collectors.toUnmodifiableList());
    }
}
