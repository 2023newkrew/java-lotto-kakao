package lotto.view;

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

    public List<Integer> getAnswerLottoInput() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return getLottoInput();
    }

    private List<Integer> getLottoInput() {
        String answerNumbers = sc.nextLine();
        return Arrays.stream(answerNumbers.split("[\\s,]+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public int getBonusBallInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(sc.nextLine());
    }
}
