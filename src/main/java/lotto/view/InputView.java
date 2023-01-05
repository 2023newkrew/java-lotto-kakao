package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int getMoney() {
        ResultView.print("구입금액을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<Integer> getWinningNumbers() {
        ResultView.print("\n지난 주 당첨 번호를 입력해 주세요.");
        return getNumbers();
    }

    public static int getBonusNumber() {
        ResultView.print("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<List<Integer>> getManualNumbersList() {
        int quantity = getManualQuantity();
        return getManualNumbersListByQuantity(quantity);
    }

    private static int getManualQuantity() {
        ResultView.print("\n수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    private static List<List<Integer>> getManualNumbersListByQuantity(int quantity) {
        ResultView.print("\n수동으로 구매할 번호를 입력해 주세요.");
        return Stream.generate(InputView::getNumbers).limit(quantity)
                .collect(Collectors.toList());
    }

    private static List<Integer> getNumbers() {
        return Arrays.stream(scanner.nextLine().split(","))
                .map(String::strip)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
