package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private final Scanner sc = new Scanner(System.in);

    public int getExpenseInput() {
        System.out.println("구입 금액을 입력해 주세요.");
        return Integer.parseInt(sc.nextLine());
    }

    public int getManualAmount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(sc.nextLine());
    }

    public List<List<Integer>> getManualLottosInput(int amount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> manualLottosInput = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            String numbers = sc.nextLine();
            manualLottosInput.add(
                    Arrays.stream(numbers.split("[,\\s]"))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())
            );
        }
        return manualLottosInput;
    }

    public List<Integer> getAnswerLottoInput() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String answerNumbers = sc.nextLine();
        return Arrays.stream(answerNumbers.split("[,\\s]"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

    }

    public int getBonusBallInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(sc.nextLine());
    }
}
