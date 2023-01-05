package lotto.view;

import java.util.*;
import java.util.stream.Collectors;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public Integer inputPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public Integer inputManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public List<Integer> inputMainNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();
        return toList(input);
    }

    public Integer inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine().trim());
    }

    private List<Integer> toList(String inputNumbers) {
        String[] slicedNumbers = inputNumbers.replaceAll(" ", "").split(",");
        return Arrays.stream(slicedNumbers)
                .map(Integer::parseInt)
                .collect(Collectors.toList())
                ;
    }
}
