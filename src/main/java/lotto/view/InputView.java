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

    public Integer inputManualLottoCount() {
        System.out.println();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextInt();
    }

    public void messageRequireInputManualLottoNumbers() {
        System.out.println();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        scanner.nextLine();
    }

    public List<Integer> inputManualLottoNumbers() {
        String input = scanner.nextLine();
        String[] mainNumbers = input.replaceAll(" ", "").split(",");
        return Arrays.stream(mainNumbers)
                .map(Integer::parseInt)
                .collect(Collectors.toList())
                ;
    }


    public List<Integer> inputWinningLottoNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();
        String[] winningLottoNumbers = input.replaceAll(" ", "").split(",");
        return Arrays.stream(winningLottoNumbers)
                .map(Integer::parseInt)
                .collect(Collectors.toList())
                ;
    }

    public Integer inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }
}
