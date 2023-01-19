package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.NumberList;

public class InputView {

    private final Scanner sc = new Scanner(System.in);

    public int getExpenseInput() {
        System.out.println("구입 금액을 입력해 주세요.");
        return Integer.parseInt(sc.nextLine());
    }

    public NumberList getAnswerLottoInput() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return getLottoInput();
    }

    public NumberList getUserLottoInput() {
        return getLottoInput();
    }

    public int getManualLottoCountInput() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String manualLottoCountInput = sc.nextLine();
        return Integer.parseInt(manualLottoCountInput);
    }

    private NumberList getLottoInput() {
        String answerNumbers = sc.nextLine();
        List<Integer> integerList = Arrays.stream(answerNumbers.split("[\\s,]+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new NumberList(integerList);
    }

    public int getBonusBallInput() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(sc.nextLine());
    }
}
