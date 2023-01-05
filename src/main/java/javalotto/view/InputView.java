package javalotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {
    private Scanner scanner;

    private InputView() {
        this.scanner = new Scanner(System.in);
    }

    public static InputView newInstance() {
        return new InputView();
    }

    public int getPurchaseAmountInput() {
        System.out.println("구입금액을 입력해 주세요.");

        return Integer.parseInt(scanner.nextLine());
    }

    public int getManualPurchaseCountInput() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        return Integer.parseInt(scanner.nextLine());
    }

    public List<List<Integer>> getManualLottoNumbersInput(int count) {
        System.out.println("수동으로 구매할 로또 번호를 입력해주세요.");

        return Stream.generate(this::getLottoNumbersInput)
                .limit(count)
                .collect(Collectors.toList());
    }

    public List<Integer> getWinningNumbersInput() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        return getLottoNumbersInput();
    }

    public int getBonusNumberInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }

    private List<Integer> getLottoNumbersInput() {
        String inputNumbers = scanner.nextLine();

        return Arrays.stream(inputNumbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }


}
