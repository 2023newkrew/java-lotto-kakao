package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public Integer inputPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    public List<Integer> inputMainNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        scanner.nextLine();
        String input = scanner.nextLine();
        String[] mainNumbers = input.replaceAll(" ", "").split(",");
        return Arrays.stream(mainNumbers)
                .map(Integer::parseInt)
                .collect(Collectors.toList())
                ;
    }

    public Integer inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }
}
